package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 自屠宰/无害化申报信息表
 * </p>
 *
 * @author MCR
 * @since 2022-08-15
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_butcher_report")
@ApiModel(value = "NbmhButcherReport对象", description = "自屠宰/无害化申报信息")
public class NbmhButcherReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = {NbmhButcherReport.Save.class})
    @NotBlank(groups = {NbmhButcherReport.Update.class, NbmhButcherReport.Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 报备编号
     */
    @Schema(name = "reportNumber", description = "报备编号")
    private String reportNumber;

    /**
     * 防疫站id
     */
    @Schema(name = "preventStationId", description = "防疫站id")
    private Long preventStationId;

    /**
     * 养殖户id
     */
    @Schema(name = "farmerId", description = "养殖户id")
    private Long farmerId;

    /**
     * 养殖户名称
     */
    @Schema(name = "farmerName", description = "养殖户名称")
    private String farmerName;

    /**
     * 养殖户电话
     */
    @Schema(name = "farmerPhone", description = "养殖户电话")
    private String farmerPhone;

    /**
     * 养殖户规模
     */
    @Schema(name = "farmerType", description = "养殖户规模")
    private String farmerType;

    /**
     * 养殖户身份证号码
     */
    @Schema(name = "farmerCard", description = "养殖户身份证号码")
    private String farmerCard;

    /**
     * 养殖户头像
     */
    @Schema(name = "farmerAvatar", description = "养殖户头像")
    private String farmerAvatar;

    /**
     * 养殖户地址
     */
    @Schema(name = "farmerAddress", description = "养殖户地址")
    private String farmerAddress;

    /**
     * 耳标
     */
    @TableField(condition = SqlCondition.LIKE)
    @Schema(name = "earNo", description = "耳标号")
    private String earNo;

    /**
     * 是否有耳标 0否 1有
     */
    @Schema(name = "isEar", description = "是否有耳标 0否 1有")
    private Integer isEar;

    /**
     * 报告类型 0自屠宰报备 1无公害报备
     */
    @Schema(name = "reportType", description = "报告类型 0自屠宰报备 1无公害报备")
    private Integer reportType;

    /**
     * 是否参加过农险 0否 1是
     */
    @Schema(name = "insured", description = "是否参加过农险 0否 1是")
    private Integer insured;

    /**
     * 农险证明
     */
    @Schema(name = "insurePic", description = "农险证明")
    private String insurePic;

    /**
     * 经度
     */
    @Schema(name = "longitude", description = "经度")
    private String longitude;

    /**
     * 纬度
     */
    @Schema(name = "latitude", description = "纬度")
    private String latitude;

    /**
     * 死亡原因
     */
    @Schema(name = "deadReason", description = "死亡原因")
    private String deadReason;

    /**
     * 动物数量
     */
    @Schema(name = "animalNum", description = "动物数量")
    private Integer animalNum;

    /**
     * 动物id
     */
    @Schema(name = "animalId", description = "动物id")
    private String animalId;

    /**
     * 种类 0猪 1牛
     */
    @Schema(name = "animalType", description = "种类 0猪 1牛")
    private Integer animalType;

    /**
     * 动物图片
     */
    @Schema(name = "animalPics", description = "动物图片")
    private String animalPics;

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
     * 状态
     */
    @Schema(name = "status", description = "状态")
    private Integer status;

    /**
     * 报备人id
     */
    @Schema(name = "userId", description = "报备人id")
    private Long userId;

    /**
     * 报备人姓名
     */
    @Schema(name = "userName", description = "报备人姓名")
    private String userName;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }


}
