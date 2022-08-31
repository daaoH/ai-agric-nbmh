package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息扩展表
 * </p>
 *
 * @author yuan
 * @since 2022-08-16
 */


@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@Schema(description="用户拓展对象")
public class NbmhUserExtraInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value="id", type=IdType.AUTO)
    @Schema(name="id", description="主键id")
    private Long id;

    /**
     * 用户id
     */
    @Schema(name="userId", description="用户id")
    private Long userId;

    /**
     * 父类id
     */
    @Schema(name="parentId", description="父类id")
    private Long parentId;

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
     * 身份证号
     */
    @Schema(name="idCard", description="身份证号")
    private String idCard;

    /**
     * 真实姓名
     */
    @Schema(name="realName", description="真实姓名")
    private String realName;

    /**
     * 身份证头像面
     */
    @Schema(name="idCardFront", description="身份证头像面")
    private String idCardFront;

    /**
     * 身份证国徽面
     */
    @Schema(name="idCardBack", description="身份证国徽面")
    private String idCardBack;

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
     * 二维码
     */
    @Schema(name="qrcode", description="二维码")
    private String qrcode;

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
     * 状态 0正常 -1冻结 -2离职
     */
    @Schema(name="status", description="状态 0正常 -1冻结 -2离职")
    private int status;


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


}
