package com.hszn.nbmh.third.utils.signature.factory.signfile.signflows;


import com.hszn.nbmh.third.utils.signature.bean.ConfigInfo;
import com.hszn.nbmh.third.utils.signature.enums.RequestType;
import com.hszn.nbmh.third.utils.signature.factory.request.Request;
import com.hszn.nbmh.third.utils.signature.factory.response.CreateSignFlowResponse;

/**
 * @author 澄泓
 * @version JDK1.7
 * @description 轩辕API签署流程创建
 * @date 2020/10/28 9:39
 */
public class CreateSignFlow extends Request<CreateSignFlowResponse> {
    private boolean autoArchive;
    private String businessScene;
    private ConfigInfo configInfo;
    private Integer contractValidity;
    private Integer contractRemind;
    private Integer signValidity;
    private String initiatorAccountId;
    private String initiatorAuthorizedAccountId;

    private CreateSignFlow() {
    }

    public CreateSignFlow(String businessScene) {
        this.businessScene = businessScene;
    }

    public boolean isAutoArchive() {
        return autoArchive;
    }

    public void setAutoArchive(boolean autoArchive) {
        this.autoArchive = autoArchive;
    }

    public String getBusinessScene() {
        return businessScene;
    }

    public void setBusinessScene(String businessScene) {
        this.businessScene = businessScene;
    }

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    public Integer getContractValidity() {
        return contractValidity;
    }

    public void setContractValidity(Integer contractValidity) {
        this.contractValidity = contractValidity;
    }

    public Integer getContractRemind() {
        return contractRemind;
    }

    public void setContractRemind(Integer contractRemind) {
        this.contractRemind = contractRemind;
    }

    public Integer getSignValidity() {
        return signValidity;
    }

    public void setSignValidity(Integer signValidity) {
        this.signValidity = signValidity;
    }

    public String getInitiatorAccountId() {
        return initiatorAccountId;
    }

    public void setInitiatorAccountId(String initiatorAccountId) {
        this.initiatorAccountId = initiatorAccountId;
    }

    public String getInitiatorAuthorizedAccountId() {
        return initiatorAuthorizedAccountId;
    }

    public void setInitiatorAuthorizedAccountId(String initiatorAuthorizedAccountId) {
        this.initiatorAuthorizedAccountId = initiatorAuthorizedAccountId;
    }

    @Override
    public void build() {
        super.setUrl("/v1/signflows");
        super.setRequestType(RequestType.POST);
    }
}
