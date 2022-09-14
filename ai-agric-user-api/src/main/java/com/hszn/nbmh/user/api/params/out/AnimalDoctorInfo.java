package com.hszn.nbmh.user.api.params.out;

import com.baomidou.mybatisplus.annotation.*;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * 兽医名片详细信息响应实体
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
public class AnimalDoctorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户userId
     */
    @Schema(name = "userId", description = "用户userId")
    private Long userId;

    /**
     * 用户名
     */
    @Schema(name = "userName", description = "用户名")
    private String userName;

    /**
     * 用户头像
     */
    @Schema(name = "avatarUrl", description = "用户头像")
    private String avatarUrl;

    /**
     * 类型 1普通用户 2专家 3站长 4防疫员 5养殖户 6商家 7稽查员
     */
    @Schema(name="type", description="类型 1普通用户 2专家 3站长 4防疫员 5养殖户 6商家 7稽查员")
    private int type;

    /**
     * 身份说明：比如养殖户　散养户还是规模养殖户
     */
    @Schema(name="typeDesc", description="身份说明：比如养殖户　散养户还是规模养殖户")
    private String typeDesc;

    /**
     * 认证状态 0未认证　1认证中 2已认证 -1认证失败
     */
    @Schema(name="authStatus", description="认证状态 0未认证　1认证中 2已认证 -1认证失败")
    private int authStatus;

    /**
     * 真实姓名
     */
    @Schema(name="realName", description="真实姓名")
    private String realName;

    /**
     * 资质证书
     */
    @Schema(name="jobTags", description="职称标签")
    private String jobTags;

    /**
     * 资质证书
     */
    @Schema(name="certificate", description="资质证书")
    private String certificate;

    /**
     * 编号
     */
    @Schema(name="serialNum", description="编号")
    private String serialNum;

    /**
     * 工作年限
     */
    @Schema(name="workYear", description="工作年限")
    private String workYear;

    /**
     * 技能说明
     */
    @Schema(name="skillDesc", description="技能说明")
    private String skillDesc;

    /**
     * 省份
     */
    @Schema(name="province", description="省份")
    private String province;

    /**
     * 城市
     */
    @Schema(name="city", description="城市")
    private String city;

    /**
     * 县城
     */
    @Schema(name="town", description="县城")
    private String town;

    /**
     * 详细地址
     */
    @Schema(name="address", description="详细地址")
    private String address;

    /**
     * 营业执照
     */
    @Schema(name="businessLicense", description="营业执照")
    private String businessLicense;

    /**
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private Long preventStationId;

    /**
     * 防疫站名称
     */
    @Schema(name="preventStationName", description="防疫站名称")
    private String preventStationName;

    /**
     * 累计接诊次数
     */
    @Schema(name = "acceptOrderNum", description = "累计接诊次数")
    private Integer acceptOrderNum;

    /**
     * 兽医类型0:非官方，1:官方
     */
    @Schema(name = "doctorType", description = "兽医类型0:非官方，1:官方")
    private Integer doctorType;

    /**
     * 擅长畜种0:猪，1:牛，2：羊；3：鸡；4：鸭；5：鹅；6：狗，以逗号分割
     */
    @TableField(condition = SqlCondition.LIKE)
    @Schema(name = "goodAnimalType", description = "擅长畜种0:猪，1:牛，2：羊；3：鸡；4：鸭；5：鹅；6：狗")
    private String goodAnimalType;

    /**
     * 兽医所属地区（例：云南省昆明市官渡区）
     */
    @TableField(condition = SqlCondition.LIKE)
    @Schema(name = "area", description = "兽医所属地区（例：云南省昆明市官渡区）")
    private String area;

    /**
     * 擅长病症
     */
    @Schema(name = "goodDisease", description = "擅长病症")
    private String goodDisease;

    /**
     * 兽医证编号
     */
    @Schema(name = "certificateNo", description = "兽医证编号")
    private String certificateNo;

    /**
     * 视频问诊金额
     */
    @Schema(name = "videoMedicalPrice", description = "视频问诊金额")
    private BigDecimal videoMedicalPrice;

    /**
     * 会诊金额
     */
    @Schema(name = "medicalPrice", description = "会诊金额")
    private BigDecimal medicalPrice;

    /**
     * 上门问诊金额
     */
    @Schema(name = "doorMedicalPrice", description = "上门问诊金额")
    private BigDecimal doorMedicalPrice;

    /**
     * 经度
     */
    @Schema(name = "longitude", description = "经度")
    private Double longitude;

    /**
     * 纬度
     */
    @Schema(name = "latitude", description = "纬度")
    private Double latitude;

    /**
     * 热度（推荐度）
     */
    @Schema(name = "heatWeight", description = "热度（推荐度）")
    private Double heatWeight;

    /**
     * 状态 0正常 -1冻结
     */
    @Schema(name = "status", description = "状态 0正常 -1冻结")
    private Integer status;

}
