package com.hszn.nbmh.third.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author fubo
 * @version 1.0
 * @date 2022/01/10 14:03:48
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class AccountBookAvgPriceVo {

    @ApiModelProperty(value="冷库名称")
    private String coldStorageName;

    @ApiModelProperty(value="冷库地址")
    private String coldStorageAddr;

    @ApiModelProperty(value="平均价格")
    private BigDecimal avgPrice;

    @ApiModelProperty(value="总数量")
    private Integer totalQuantity;

    @ApiModelProperty(value="总价格")
    private BigDecimal totalPrice;

    @ApiModelProperty(value="商品")
    private String goodsName;

    @TableField(exist=false)
    @ApiModelProperty(value="平均价格")
    private List<AccountBookCategory> accountBookCategoryList;

    @ApiModelProperty(value="下单日期")
    private Date createTime;
}
