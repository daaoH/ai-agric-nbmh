package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 活体交易信息表
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
@TableName("nbmh_trade_report")
@ApiModel(value = "NbmhTradeReport对象", description = "活体交易信息")
public class NbmhTradeReport implements Serializable {

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
     * 防疫站id
     */
    @NotNull(message = "新增数据时防疫站preventStationId不能为空", groups = {Save.class})
    @Schema(name = "preventStationId", description = "防疫站id")
    private Long preventStationId;

    /**
     * 报备编号
     */
    @Schema(name = "reportNumber", description = "报备编号")
    @TableField(condition = SqlCondition.LIKE)
    private String reportNumber;

    /**
     * 报告种类 0商家报备；1养殖户报备
     */
    @Schema(name = "reportType", description = "报告种类 0商家报备；1养殖户报备")
    private Integer reportType;

    /**
     * 养殖户id
     */
    @NotNull(message = "新增数据时养殖户farmerId不能为空", groups = {Save.class})
    @Schema(name = "farmerId", description = "养殖户id")
    private Long farmerId;

    /**
     * 养殖户名称
     */
    @NotBlank(message = "新增数据时养殖户名称farmerName不能为空", groups = {Save.class})
    @Schema(name = "farmerName", description = "养殖户名称")
    private String farmerName;

    /**
     * 养殖户电话
     */
    @NotBlank(message = "新增数据时养殖户电话farmerPhone不能为空", groups = {Save.class})
    @Schema(name = "farmerPhone", description = "养殖户电话")
    private String farmerPhone;

    /**
     * 养殖户头像
     */
    @NotBlank(message = "新增数据时养殖户头像farmerAvatar不能为空", groups = {Save.class})
    @Schema(name = "farmerAvatar", description = "养殖户头像")
    private String farmerAvatar;

    /**
     * 养殖户地址
     */
    @NotBlank(message = "新增数据时养殖户地址farmerAddress不能为空", groups = {Save.class})
    @Schema(name = "farmerAddress", description = "养殖户地址")
    private String farmerAddress;

    /**
     * 买家id
     */
    @Schema(name = "buyerId", description = "买家id")
    private String buyerId;

    /**
     * 买家姓名
     */
    @NotBlank(message = "新增数据时买家姓名buyerName不能为空", groups = {Save.class})
    @Schema(name = "buyerName", description = "买家姓名")
    private String buyerName;

    /**
     * 买家身份证号码
     */
    @NotBlank(message = "新增数据时买家身份证号码buyerCard不能为空", groups = {Save.class})
    @Schema(name = "buyerCard", description = "买家身份证号码")
    private String buyerCard;

    /**
     * 买家手机号码
     */
    @NotBlank(message = "新增数据时买家手机号码buyerPhone不能为空", groups = {Save.class})
    @Schema(name = "buyerPhone", description = "买家手机号码")
    private String buyerPhone;

    /**
     * 售卖去向
     */
    @Schema(name = "sellWhere", description = "售卖去向")
    private String sellWhere;

    /**
     * 耳标
     */
    @Schema(name = "earNo", description = "耳标号")
    private String earNo;

    /**
     * 补带耳标(多个用json串)
     */
    @Schema(name = "suppleEarNo", description = "补带耳标(多个用json串)")
    private String suppleEarNo;

    /**
     * 起运地
     */
    @NotBlank(message = "新增数据时起运地transAddress不能为空", groups = {Save.class})
    @Schema(name = "transAddress", description = "起运地")
    private String transAddress;

    /**
     * 到达地
     */
    @NotBlank(message = "新增数据时到达地arrivalAddress不能为空", groups = {Save.class})
    @Schema(name = "arrivalAddress", description = "到达地")
    private String arrivalAddress;

    /**
     * 经度(方便计算距离)
     */
    @Schema(name = "longitude", description = "经度(方便计算距离)")
    private String longitude;

    /**
     * 纬度(方便计算距离)
     */
    @Schema(name = "latitude", description = "纬度(方便计算距离)")
    private String latitude;

    /**
     * 动物数量
     */
    @Schema(name = "animalNum", description = "动物数量")
    private Integer animalNum;

    /**
     * 动物id
     */
    @NotBlank(message = "新增数据时动物animalId不能为空", groups = {Save.class})
    @TableField(condition = SqlCondition.LIKE)
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


    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }

}
