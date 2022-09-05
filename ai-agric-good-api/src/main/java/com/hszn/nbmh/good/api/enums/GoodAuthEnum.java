package com.hszn.nbmh.good.api.enums;

/**
 * @Author：袁德民
 * @Description:　审核状态
 * @Date:下午8:27 22/9/2
 * @Modified By:
 */
public enum GoodAuthEnum {

    /**
     * 待审核
     */
    CHECKING(0, "待审核"),

    /**
     * 审核通过
     */
    PASS(1, "审核通过"),

    /**
     * 拒绝
     */
    REFUSE(-1, "拒绝");

    private final String description;

    private final Integer code;

    GoodAuthEnum(Integer code, String description){
        this.code = code;
        this.description = description;
    }

    public String description(){
        return description;
    }

    public Integer code(){
        return code;
    }
}
