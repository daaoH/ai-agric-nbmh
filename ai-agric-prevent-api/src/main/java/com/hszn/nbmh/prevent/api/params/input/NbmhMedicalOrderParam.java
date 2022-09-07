package com.hszn.nbmh.prevent.api.params.input;

import com.hszn.nbmh.prevent.api.entity.NbmhMedicalAccept;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 诊断下单记录 表单提交参数实体
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
public class NbmhMedicalOrderParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 视频诊疗会议房间号
     */
    @Schema(name = "roomNumber", description = "视频诊疗会议房间号")
    private String roomNumber;

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
     * 诊断金额
     */
    @Schema(name = "medicalMoney", description = "诊断金额")
    private BigDecimal medicalMoney;

    /**
     * 诊断定金
     */
    @Schema(name = "frontMoney", description = "诊断定金")
    private BigDecimal frontMoney;

    /**
     * 是否已支付定金
     */
    @Schema(name = "payFrontMoney", description = "是否已支付定金")
    private Integer payFrontMoney;

    /**
     * 是否已支付所有费用
     */
    @Schema(name = "payMedicalMoney", description = "是否已支付所有费用")
    private Integer payMedicalMoney;

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
     * 会诊接单记录列表
     */
    @Schema(name = "medicalAcceptList", description = "会诊接单记录列表")
    List<NbmhMedicalAccept> medicalAcceptList;

}
