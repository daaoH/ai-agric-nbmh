package com.hszn.nbmh.third.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author DAWANG
 * @since 2021-12-27
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ApiModel(value="OtherExpenseBook对象", description="")
public class OtherExpenseBook implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value="id")
    @TableId(value="id", type=IdType.AUTO)
    private Long id;

    @ApiModelProperty(value="冷库id")
    private Long coldStorageId;

    @ApiModelProperty(value="冷库名称")
    private String coldStorageName;

    @ApiModelProperty(value="冷库地址")
    private String coldStorageAddr;

    @ApiModelProperty(value="其他费用")
    private BigDecimal otherPrice;

    @ApiModelProperty(value="已支付费用")
    private BigDecimal paidPrice;

    @ApiModelProperty(value="未支付费用")
    private BigDecimal unPaidPrice;

    @TableField(exist = false)
    @ApiModelProperty(value="付款金额")
    private BigDecimal payment;

    @ApiModelProperty(value="收款方")
    private String payee;

    @ApiModelProperty(value="经办人")
    private String manager;

    @ApiModelProperty(value="状态(0->未完成  1->已完成)")
    private String status;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="修改时间")
    private Date modifyTime;

    @ApiModelProperty(value="是否删除（‘true’=已删除，‘false’=未删除）")
    private String isDel;

    @ApiModelProperty(value="操作人")
    private String operator;

    @ApiModelProperty(value="操作人id")
    private Long operatorId;

    @ApiModelProperty(value="机构id")
    private String orgCode;

    @ApiModelProperty(value="描述")
    private String body;

    @ApiModelProperty(value="花费日期")
    private String date;

    @ApiModelProperty(value="用途")
    private String purpose;

    @ApiModelProperty(value="冷库字典id")
    private Long dicId;

    @ApiModelProperty(value="数量")
    private int num;

    @ApiModelProperty(value="单位")
    private String unit;

    @ApiModelProperty(value="冷库字典id")
    private BigDecimal price;

    @ApiModelProperty(value="字典名称")
    private String dicName;
}
