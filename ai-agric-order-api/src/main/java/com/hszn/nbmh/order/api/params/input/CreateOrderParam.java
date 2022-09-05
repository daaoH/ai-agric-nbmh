package com.hszn.nbmh.order.api.params.input;

import com.hszn.nbmh.good.api.params.vo.ShopCartItemVo;
import com.hszn.nbmh.marketing.api.entity.NbmhCoupon;
import com.hszn.nbmh.order.api.entity.NbmhOrder;
import com.hszn.nbmh.order.api.entity.NbmhReceipt;
import com.hszn.nbmh.user.api.entity.NbmhUserAddress;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午2:38 22/9/4
 * @Modified By:
 */
@Schema(description = "创建订单参数")
@Data
public class CreateOrderParam implements Serializable {

    /**
     * 购物车商品项
     */
    private List<ShopCartItemVo> items;

    /**
     *　收货人地址
     */
    private NbmhUserAddress userAddress;

    /**
     * 优惠券
     */
    private NbmhCoupon coupon;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 活动类型
     */
    private Integer activityType;

    /**
     * 发票信息
     */
    private NbmhReceipt receipt;

    /**
     * 用户留言
     */
    private String userNote;
}
