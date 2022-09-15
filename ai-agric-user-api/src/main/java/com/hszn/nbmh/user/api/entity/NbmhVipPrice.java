package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户vip价格对照表
 * </p>
 *
 * @author 李肖
 * @since 2022-09-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_vip_price")
@ApiModel(value = "NbmhVipPrice", description = "用户vip价格对照")
public class NbmhVipPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(name = "id", description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 等级
     */
    @Schema(name = "level", description = "等级")
    private Integer level;

    /**
     * 名称
     */
    @Schema(name = "title", description = "名称")
    private String title;

    /**
     * 价格
     */
    @Schema(name = "price", description = "价格")
    private BigDecimal price;

    /**
     * 有效期
     */
    @Schema(name = "validDate", description = "有效期")
    private String validDate;

    /**
     * 创建日期
     */
    @Schema(name = "createTime", description = "创建日期")
    private Date createTime;

    /**
     * 更新日期
     */
    @Schema(name = "updateTime", description = "更新日期")
    private Date updateTime;

    /**
     * 状态 0正常 -1删除
     */
    @Schema(name = "status", description = "状态")
    private Integer status;


}
