package com.hszn.nbmh.third.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 快递物流信息实体
 * </p>
 *
 * @author MCR
 * @since 2022-08-26
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class KdSearchEntity  implements Serializable {
    /**
     *用户ID
     */
    private String EBusinessID;

    /**
     *订单编号
     */
    private String OrderCode;

    /**
     *快递公司编码
     */
    private String ShipperCode;

    /**
     *物流运单号
     */
    private String LogisticCode;

    /**
     *成功与否
     */
    private boolean Success;

    /**
     *失败原因
     */
    private String Reason;

    /**
     *物流状态：0-暂无轨迹信息,1-已揽收,2-在途中,3-签收,4-问题件
     */
    private String State;

    /**
     *增值物流状态：
     * 0-暂无轨迹信息
     * 1-已揽收
     * 2-在途中
     * 201-到达派件城市, 202-派件中, 211-已放入快递柜或驿站,
     * 3-已签收
     * 301-正常签收, 302-派件异常后最终签收, 304-代收签收, 311-快递柜或驿站签收,
     * 4-问题件
     * 401-发货无信息, 402-超时未签收, 403-超时未更新, 404-拒收(退件), 405-派件异常, 406-退货签收, 407-退货未签收, 412-快递柜或驿站超时未取
     */
    private String StateEx;

    /**
     *所在城市
     */
    private String Location;

    /**
     *快递踪迹
     */
    private List<Traces> Traces;

    /**
     *配送人
     */
    private String DeliveryMan;

}
