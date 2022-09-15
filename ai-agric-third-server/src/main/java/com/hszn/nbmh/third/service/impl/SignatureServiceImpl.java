package com.hszn.nbmh.third.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hszn.nbmh.common.security.service.AuthUser;
import com.hszn.nbmh.common.security.util.SecurityUtils;
import com.hszn.nbmh.shop.api.feign.RemoteShopInfoService;
import com.hszn.nbmh.third.entity.NbmhSignatureHistory;
import com.hszn.nbmh.third.entity.NbmhSignatureInfo;
import com.hszn.nbmh.third.service.INbmhSignatureHistoryService;
import com.hszn.nbmh.third.service.INbmhSignatureInfoService;
import com.hszn.nbmh.third.service.SignatureService;
import com.hszn.nbmh.third.utils.signature.bean.*;
import com.hszn.nbmh.third.utils.signature.exception.DefineException;
import com.hszn.nbmh.third.utils.signature.factory.Factory;
import com.hszn.nbmh.third.utils.signature.factory.account.CreatePersonByThirdPartyUserId;
import com.hszn.nbmh.third.utils.signature.factory.base.Account;
import com.hszn.nbmh.third.utils.signature.factory.base.SignFile;
import com.hszn.nbmh.third.utils.signature.factory.response.CreateFlowOneStepResponse;
import com.hszn.nbmh.third.utils.signature.factory.response.CreatePersonByThirdPartyUserIdResponse;
import com.hszn.nbmh.third.utils.signature.factory.response.QrySignFieldResponse;
import com.hszn.nbmh.third.utils.signature.factory.signfile.CreateFlowOneStep;
import com.hszn.nbmh.third.utils.signature.factory.signfile.signfields.QrySignField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * 签名服务
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-08 14:46
 */
@Service("signatureService")
public class SignatureServiceImpl implements SignatureService {

    @Value("${signature.host:https://smlopenapi.esign.cn}")
    private String host;

    @Value("${signature.app_id:7438932463}")
    private String appId;

    @Value("${signature.app_secret:ef5c377e99b85d213ef9feb5e00479a1}")
    private String appSecret;

    @Value("${signature.debug:fault}")
    private boolean debug;

    @Value("${signature.callback_url}")
    private String callbackUrl;

    @Resource
    private INbmhSignatureInfoService signatureInfoService;

    @Resource
    private RemoteShopInfoService shopInfoService;

    @Resource
    private INbmhSignatureHistoryService signatureHistoryService;

    @PostConstruct
    void init() {
        Factory.setHost(host);
        Factory.setAppSecret(appSecret);
        Factory.setAppId(appId);
        Factory.setDebug(debug);
    }

    @Override
    public void signatureStart() throws DefineException {
        AuthUser user = SecurityUtils.getUser();
        if (user == null) {
            throw new RuntimeException("获取用户信息失败");
        }
        Long userId = user.getId();
        //根据用户查询是否已经存在用户信息,存在则直接返回
        String accountId;
        LambdaQueryWrapper<NbmhSignatureInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NbmhSignatureInfo::getUserId, userId);
        NbmhSignatureInfo info = signatureInfoService.getOne(wrapper);
        if (info != null) {
            return;
        }
        //如果没有则查询用户基本信息,发起签名并保存
        // Result<NbmhShopInfo> nbmhShopInfoResult = shopInfoService.currentShopInfo();
        Long shopId = 345L;
        CreatePersonByThirdPartyUserId createPsn = Account.createPersonByThirdPartyUserId(
                String.valueOf(userId),
                "李伟",
                "CRED_PSN_CH_IDCARD",
                "530127199209091733");
        createPsn.setMobile("18987284523");
        CreatePersonByThirdPartyUserIdResponse createPsnResp = createPsn.execute();
        accountId = createPsnResp.getData().getAccountId();
        //签署服务
        String flowId = createFlowOneStep(getFileId(), accountId);
        info = new NbmhSignatureInfo();
        info.setUserId(userId);
        info.setShopId(shopId);
        info.setAccountId(accountId);
        info.setFlowId(flowId);
        info.setStatus(0);
        signatureInfoService.save(info);
    }

    @Override
    public String createFlowOneStep(String fileId, String accountId) throws DefineException {
        //入参是array格式时，先构造对象参数的array
        Docs docs = new Docs();
        //向array传入对象
        docs.add(new Doc().setFileId(fileId));
        //flowInfo参数
        FlowInfo flowInfo = new FlowInfo().setBusinessScene("b2c合同签署测试")
                //启用自动归档
                .setAutoArchive(true)
                //启用自动开启流程
                .setAutoInitiate(true);

        FlowConfigInfo configInfo = new FlowConfigInfo();
        //回调地址
        configInfo.setNoticeDeveloperUrl(callbackUrl);
        flowInfo.setFlowConfigInfo(configInfo);
        Signfields psnSignfields = new Signfields();
        psnSignfields.add(new Signfield()
                .setFileId(fileId)
                .setPosBean(new PosBean()
                        .setPosPage("1")
                        .setPosX(200)
                        //构造个人signfields参数对象,用于后续入参使用,支持链式入参
                        .setPosY(20)).setSignDateBeanType(1));

        Signfields orgSignfields = new Signfields();
        orgSignfields.add(new Signfield()
                .setFileId(fileId)
                //机构签署必传
                .setActorIndentityType("2")
                .setAutoExecute(true)
                .setPosBean(new PosBean()
                        .setPosPage("1")
                        .setPosX(500)
                        //构造企业signfields参数对象,用于后续入参使用,支持链式入参
                        .setPosY(20)).setSignDateBeanType(1));

        Signers signers = new Signers();
        signers.add(new Signer()
                //传入企业signer信息
                .setPlatformSign(true).setSignFields(orgSignfields).setSignOrder(1));
        signers.add(new Signer()
                .setSignerAccount(
                        new SignerAccount()
                                .setSignerAccountId(accountId)
                        //传入个人signer信息
                ).setSignFields(psnSignfields).setSignOrder(2));
        CreateFlowOneStep flowOneStep = SignFile.createFlowOneStep(docs, flowInfo, signers);
        CreateFlowOneStepResponse flowOneStepResp = flowOneStep.execute();
        return flowOneStepResp.getData().getFlowId();
    }

    @Override
    public String getFileId() {
        return "d20f6aa88e4c4254bb3083477d06253e";
    }

    /**
     * 签名回调处理
     *
     * @param bean
     */
    @Override
    public void callbackHandler(CallBackBean bean) throws DefineException {
        NbmhSignatureHistory history = new NbmhSignatureHistory();
        String thirdPartyUserId = bean.getThirdPartyUserId();
        history.setAccountId(bean.getAccountId());
        history.setAction(bean.getAction());
        history.setFlowId(bean.getFlowId());
        history.setAccountId(bean.getAccountId());
        history.setSignTime(bean.getSignTime());
        history.setThirdPartyUserId(StringUtils.hasText(thirdPartyUserId) ? Long.parseLong(thirdPartyUserId) : null);
        signatureHistoryService.save(history);
        //用户阅读操作
        if (Objects.equals("PARTICIPANT_MARKREAD", history.getAction())) {
            UpdateWrapper<NbmhSignatureInfo> wrapper = new UpdateWrapper<>();
            wrapper.lambda().set(NbmhSignatureInfo::getStatus, 1).eq(NbmhSignatureInfo::getFlowId, history.getFlowId());
            signatureInfoService.update(wrapper);
        }
        //签署完成,修改状态及签署文件
        if (Objects.equals("SIGN_FLOW_FINISH", history.getAction())) {
            QrySignField qrySignField = new QrySignField(history.getFlowId());
            QrySignFieldResponse execute = qrySignField.execute();
            UpdateWrapper<NbmhSignatureInfo> wrapper = new UpdateWrapper<>();
            wrapper.lambda().set(NbmhSignatureInfo::getStatus, 2)
                    .set(NbmhSignatureInfo::getFileUrl, execute.getData().getFileUrl()).eq(NbmhSignatureInfo::getFlowId, history.getFlowId());
            signatureInfoService.update(wrapper);
        }
    }

    @Override
    public NbmhSignatureInfo currentInfo() {
        QueryWrapper<NbmhSignatureInfo> wrapper = new QueryWrapper<>();
        AuthUser user = SecurityUtils.getUser();
        if (user == null) {
            throw new RuntimeException("获取用户信息失败");
        }
        Long userId = user.getId();
        wrapper.lambda().eq(NbmhSignatureInfo::getUserId, userId);
        return signatureInfoService.getOne(wrapper);
    }
}
