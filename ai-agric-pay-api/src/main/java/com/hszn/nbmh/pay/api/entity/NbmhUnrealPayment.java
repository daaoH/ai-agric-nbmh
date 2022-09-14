package com.hszn.nbmh.pay.api.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;

/**
 * <p>
 * 虚拟支付信息表
 * </p>
 *
 * @author MCR
 * @since 2022-09-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_unreal_payment")
@ApiModel(value = "NbmhUnrealPayment对象", description = "虚拟支付信息记录")
public class NbmhUnrealPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(message = "新增数据时Id必须为null", groups = {Save.class})
    @NotNull(message = "更新或删除数据时Id不能为空", groups = {Update.class, Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 租户标识
     */
    @Schema(name = "tenantId", description = "租户标识")
    private String tenantId;

    /**
     * 支付渠道:1-农牧币 2积分
     */
    @Schema(name = "payChannel", description = "支付渠道:1-农牧币 2积分")
    private Integer payChannel;

    /**
     * 支付用户
     */
    @NotNull(message = "新增数据时支付用户payUserId不能为空", groups = {Save.class})
    @Schema(name = "payUserId", description = "支付用户userId")
    private Long payUserId;

    /**
     * 收入用户
     */
    @NotNull(message = "新增数据时收入用户incomeUserId不能为空", groups = {Save.class})
    @Schema(name = "incomeUserId", description = "收入用户userId")
    private Long incomeUserId;

    /**
     * 订单号
     */
    @NotNull(message = "新增数据时订单号orderId不能为空", groups = {Save.class})
    @Schema(name = "orderId", description = "订单号")
    private Long orderId;

    /**
     * 订单总金额，精确到小数点后两位
     */
    @Schema(name = "totalMoney", description = "订单总金额，精确到小数点后两位")
    private BigDecimal totalMoney;

    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。
     */
    @Schema(name = "payEndTime", description = "该笔订单允许的最晚付款时间，逾期将关闭交易。")
    private Date payEndTime;

    /**
     * 交易类型
     */
    @Schema(name = "tranType", description = "交易类型")
    private Integer tranType;

    /**
     * 交易状态：1（交易创建，等待买家付款）、2（未付款交易超时关闭）、3（交易支付成功）
     */
    @Schema(name = "tradeStatus", description = "交易状态：1（交易创建，等待买家付款）、2（未付款交易超时关闭）、3（交易支付成功）")
    private Integer tradeStatus;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;

    /**
     * 状态 0正常 -1删除
     */
    @Schema(name = "status", description = "状态 0正常 -1删除")
    private Integer status;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
