package com.hszn.nbmh.third.utils.signature.bean;

import java.util.Date;

/**
 * @author liwei
 * @version 1.0
 * @since 2022-09-08 13:47
 */
public class CallBackBean {
    private String action;
    private String flowId;
    private String accountId;
    private String authorizedAccountId;
    private Date signTime;
    private Integer order;
    private Integer signResult;
    private String thirdOrderNo;
    private String resultDescription;
    private Long timestamp;
    private String thirdPartyUserId;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAuthorizedAccountId() {
        return authorizedAccountId;
    }

    public void setAuthorizedAccountId(String authorizedAccountId) {
        this.authorizedAccountId = authorizedAccountId;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getSignResult() {
        return signResult;
    }

    public void setSignResult(Integer signResult) {
        this.signResult = signResult;
    }

    public String getThirdOrderNo() {
        return thirdOrderNo;
    }

    public void setThirdOrderNo(String thirdOrderNo) {
        this.thirdOrderNo = thirdOrderNo;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getThirdPartyUserId() {
        return thirdPartyUserId;
    }

    public void setThirdPartyUserId(String thirdPartyUserId) {
        this.thirdPartyUserId = thirdPartyUserId;
    }

    @Override
    public String toString() {
        return "CallBackBean{" +
                "action='" + action + '\'' +
                ", flowId='" + flowId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", authorizedAccountId='" + authorizedAccountId + '\'' +
                ", signTime=" + signTime +
                ", order=" + order +
                ", signResult=" + signResult +
                ", thirdOrderNo='" + thirdOrderNo + '\'' +
                ", resultDescription='" + resultDescription + '\'' +
                ", timestamp=" + timestamp +
                ", thirdPartyUserId='" + thirdPartyUserId + '\'' +
                '}';
    }
}
