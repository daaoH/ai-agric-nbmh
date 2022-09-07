package com.hszn.nbmh.prevent.api.params.out;


import com.hszn.nbmh.common.core.utils.PageModelUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/9/6
 * @Modified By:
 */
@Schema(name="(扫码)活体报备返回结果")
@Data
public class TradeReportPageResult<T> {


    /**
     * 农户id
     */
    @Schema(name="farmerId", description="农户id")
    private long farmerId;

    /**
     * 被邀请人(农户名字)
     */
    @Schema(name="farmerName", description="农户名字")
    private String farmerName;

    /**
     * 农户地址
     */
    @Schema(name="farmerAddress", description="农户地址")
    private String farmerAddress;

    /**
     * 农户身份证号
     */
    @Schema(name="farmerIdCard", description="农户身份证号")
    private String farmerIdCard;

    /**
     * 农户头像
     */
    @Schema(name="farmerAvatarUrl", description="农户头像")
    private String farmerAvatarUrl;


    /**
     * 农户手机号
     */
    @Schema(name="farmerPhone", description="农户手机号")
    private String farmerPhone;


    /**
     * 商贩id
     */
    @Schema(name="buyerId", description="商贩id")
    private long buyerId;

    /**
     * 商贩名字
     */
    @Schema(name="buyerName", description="商贩id")
    private String buyerName;

    /**
     * 商贩手机号
     */
    @Schema(name="buyerPhone", description="商贩id")
    private String buyerPhone;

    /**
     * 商贩身份证号
     */
    @Schema(name="buyerIdCard", description="商贩身份证号")
    private String buyerIdCard;

    /**
     * 当前动物类型
     */
    @Schema(name="type", description="当前动物类型")
    private int type;


    /**
     * 类型集合
     */
    @Schema(name="types", description="类型集合")
    private List<Integer> types;

    /**
     * 分页结果集
     */
    @Schema(name="list", description="分页结果集")
    private List<T> list;

    /**
     * 分页结果集
     */
    @Schema(name="pageModelUtils", description="分页结果集")
    private PageModelUtils pageModelUtils;

}
