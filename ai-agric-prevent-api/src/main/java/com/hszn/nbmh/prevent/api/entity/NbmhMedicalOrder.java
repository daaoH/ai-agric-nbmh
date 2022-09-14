package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 诊断下单记录
 * </p>
 *
 * @author MCR
 * @since 2022-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_medical_order")
@ApiModel(value = "NbmhMedicalOrder对象", description = "诊断下单记录")
public class NbmhMedicalOrder implements Serializable {

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
     * 视频诊疗会议房间号
     */
    @Schema(name = "roomNumber", description = "视频诊疗会议房间号")
    private String roomNumber;

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
     * 动物类型 0猪 1牛
     */
    @Schema(name = "animalType", description = "动物类型 0猪 1牛")
    private Integer animalType;

    /**
     * 问诊类型 0视频问诊，1专家问诊，2预约上门问诊
     */
    @Schema(name = "medicalType", description = "问诊类型 0视频问诊，1专家问诊，2预约上门问诊")
    private Integer medicalType;

    /**
     * 会诊时间
     */
    @Schema(name = "medicalTime", description = "会诊时间")
    private Date medicalTime;

    /**
     * 病情描述
     */
    @NotBlank(message = "新增数据时病情描述illnessDesc不能为空", groups = {Save.class})
    @Schema(name = "illnessDesc", description = "病情描述")
    private String illnessDesc;

    /**
     * 病情描述图片地址
     */
    @Schema(name = "picUrl", description = "病情描述图片地址")
    private String picUrl;

    /**
     * 是否免费诊疗 0:收费 1:免费
     */
    @Schema(name = "freeStatus", description = "是否免费诊疗 0:收费 1:免费")
    private Integer freeStatus;

    /**
     * 诊疗费用总金额
     */
    @Schema(name = "medicalMoney", description = "诊疗费用总金额")
    private BigDecimal medicalMoney;

    /**
     * 诊疗费用定金
     */
    @Schema(name = "frontMoney", description = "诊疗费用定金")
    private BigDecimal frontMoney;

    /**
     * 是否已支付定金
     */
    @Schema(name = "isPayFrontMoney", description = "是否已支付定金,0:否；1：是；")
    private Integer isPayFrontMoney;

    /**
     * 是否已支付所有费用
     */
    @Schema(name = "isPayMedicalMoney", description = "是否已支付所有费用,0:否；1：是；")
    private Integer isPayMedicalMoney;

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
