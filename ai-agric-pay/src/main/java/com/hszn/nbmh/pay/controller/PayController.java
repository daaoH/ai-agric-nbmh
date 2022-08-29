package com.hszn.nbmh.pay.controller;


import com.alipay.api.AlipayApiException;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.pay.api.params.input.PayOrderVo;
import com.hszn.nbmh.pay.service.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/nbmh-payment")
@Api(tags="支付")
public class PayController {

    private final PayService payService;

    /**
     * 微信支付
     *
     * @return
     */
    @PostMapping("/wxPay")
    @ApiOperation(value="微信支付")
    public Result wxPay(HttpServletRequest request, @RequestBody PayOrderVo payOrderVo) throws Exception {
        return Result.ok(payService.wxPay(request, payOrderVo));
    }


    /**
     * 微信回调
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    /**
     * 微信支付异步结果通知
     */
    @RequestMapping(value="/wxapppay/notify", method={RequestMethod.GET, RequestMethod.POST})
    public String wxAppCallBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String resXml="";
        try {
            InputStream inputStream=request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb=new StringBuilder();
            String line=null;
            try {
                while ((line=reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return payService.wxAppCallBack(resXml=sb.toString());
        } catch (Exception e) {
            return "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
    }

    /**
     * 支付宝支付
     *
     * @return
     */
    @PostMapping("/aliPay")
    @ApiOperation(value="支付宝支付")
    public Result aliPay(HttpServletRequest request, @RequestBody PayOrderVo payOrderVo) throws IOException, AlipayApiException {
        return Result.ok(payService.aliPay(payOrderVo));
    }

    /**
     * 支付宝回调
     *
     * @param request
     * @return
     */
    @RequestMapping("/alipay/notify")
    public Object aliPayNotify(HttpServletRequest request) {
        try {
            return payService.aliPayCallback(request);
        } catch (Exception e) {
            return "fail";
        }
    }



    /**
     * 申请退款
     *
     * @param orderId
     * @throws Exception
     */
    @PutMapping("/refunds/{orderDetailsId}")
    //@PreAuthorize("hasAuthority('coldStorage:update')")
    @ApiOperation(value="商户同意退款")
    @ApiImplicitParams({@ApiImplicitParam(name="orderDetailsId", value="订单id")})
    public void refunds(@PathVariable Long orderId) throws Exception {
        payService.refunds(orderId);
    }


}
