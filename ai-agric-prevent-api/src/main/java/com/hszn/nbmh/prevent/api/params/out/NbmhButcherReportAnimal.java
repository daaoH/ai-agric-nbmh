package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 防疫站工作人员根据记录ID查询屠宰/无害化申报信息 动物列表实体
 * </p>
 *
 * @author MCR
 * @since 2022-09-05
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NbmhButcherReportAnimal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动物Id
     */
    @Schema(name = "animalId", description = "动物Id")
    private Long animalId;

    /**
     * 耳标号
     */
    @Schema(name = "earNo", description = "耳标号")
    private String earNo;

    /**
     * 种类 0猪 1牛
     */
    @Schema(name = "type", description = "种类 0猪 1牛")
    private int type;

    /**
     * 品种
     */
    @Schema(name = "category", description = "品种")
    private String category;

    /**
     * 月龄
     */
    @Schema(name = "age", description = "月龄")
    private Integer age;

    /**
     * 重量
     */
    @Schema(name = "weight", description = "重量")
    private Float weight;

    /**
     * 是否参加过农险 0否 1是
     */
    @Schema(name = "insured", description = "是否参加过农险 0否 1是")
    private int insured;

    /**
     * 农险证明
     */
    @Schema(name = "insurePic", description = "农险证明")
    private String insurePic;

    /**
     * 农户id
     */
    @Schema(name = "userId", description = "农户id")
    private Long userId;

    /**
     * 养殖场id
     */
    @Schema(name = "farmId", description = "养殖场id")
    private Long farmId;

    /**
     * 图片
     */
    @Schema(name = "photos", description = "图片")
    private String photos;

    /**
     * 动物信息二维码
     */
    @Schema(name = "qrcode", description = "动物信息二维码")
    private String qrcode;

    /**
     * 死亡原因
     */
    @Schema(name = "deadReason", description = "死亡原因")
    private String deadReason;

    /**
     * 状态 0正常 1已抵押 2自屠宰 3无公害 4检疫
     */
    @Schema(name="status", description="状态 0正常 1已抵押 2自屠宰 3无公害 4检疫")
    private Integer status;

}
