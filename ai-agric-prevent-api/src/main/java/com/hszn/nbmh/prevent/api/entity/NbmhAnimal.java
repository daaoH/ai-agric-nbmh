package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 动物信息表
 * </p>
 *
 * @author wangjun
 * @since 2022-08-16
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(name="动物对象")
public class NbmhAnimal implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value="id", type=IdType.AUTO)
    @Schema(name="id", description="主键id")
    private Long id;

    /**
     * 耳标号
     */
    @Schema(name="earNo", description="耳标号")
    private String earNo;

    /**
     * 种类 0猪 1牛
     */
    @Schema(name="type", description="种类 0猪 1牛")
    private int type;

    /**
     * 品种
     */
    @Schema(name="category", description="品种")
    private String category;

    /**
     * 月龄
     */
    @Schema(name="age", description="月龄")
    private Integer age;

    /**
     * 重量
     */
    @Schema(name="weight", description="重量")
    private Float weight;

    /**
     * 是否参加过农险 0否 1是
     */
    @Schema(name="insured", description="是否参加过农险 0否 1是")
    private int insured;

    /**
     * 农险证明
     */
    @Schema(name="insurePic", description="农险证明")
    private String insurePic;

    /**
     * 农户id
     */
    @Schema(name="userId", description="农户id")
    private Long userId;

    /**
     * 养殖场id
     */
    @Schema(name="farmId", description="养殖场id")
    private Long farmId;

    /**
     * 图片
     */
    @Schema(name="photos", description="图片")
    private String photos;

    /**
     * 动物信息二维码
     */
    @Schema(name="qrcode", description="动物信息二维码")
    private String qrcode;

    /**
     * 备注
     */
    @Schema(name="remark", description="备注")
    private String remark;

    /**
     * 创建时间
     */
    @Schema(name="createTime", description="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(name="updateTime", description="更新时间")
    private Date updateTime;

    /**
     * 状态 0正常 1已抵押 2自屠宰 3无公害 4检疫
     */
    @Schema(name="status", description="状态 0正常 1已抵押 2自屠宰 3无公害 4检疫")
    private Integer status;


}
