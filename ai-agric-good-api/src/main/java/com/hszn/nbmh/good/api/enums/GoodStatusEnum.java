package com.hszn.nbmh.good.api.enums;

/**
 * @Author：袁德民
 * @Description:　商品状态
 * @Date:下午8:34 22/9/2
 * @Modified By:
 */
public enum GoodStatusEnum {
    DOWN(0, "下架"),
    UP(1, "上架"),
    SELLOUT(2, "售完");

    private final Integer code;
    private final String description;

    GoodStatusEnum(Integer code, String description){
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
