package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 稽查上报
 * </p>
 *
 * @author wangjun
 * @since 2022-08-25
 */

@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@Schema(name="稽查对象")
public class NbmhCheck implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value="id", type=IdType.AUTO)
    @Schema(name="id", description="主键id")
    private Long id;

    /**
     * 稽查id
     */
    @Schema(name="checkId", description="稽查id")
    private Long checkId;


    /**
     * 稽查编号
     */
    @Schema(name="checkNumber", description="稽查编号")
    private String checkNumber;

    /**
     * 运输人车牌号
     */
    @Schema(name="carNumber", description="运输人车牌号")
    private String carNumber;

    /**
     * 运输人
     */
    @Schema(name="transporter", description="运输人")
    private String transporter;

    /**
     * 运输人电话
     */
    @Schema(name="phone", description="运输人电话")
    private String phone;

    /**
     * 身份证正面
     */
    @Schema(name="idCardFrontUrl", description="身份证正面")
    private String idCardFrontUrl;

    /**
     * 身份证反面
     */
    @Schema(name="idCardBackUrl", description="身份证反面")
    private String idCardBackUrl;

    /**
     * 到达地
     */
    @Schema(name="destination", description="到达地")
    private String destination;

    /**
     * 启运地
     */
    @Schema(name="placeConsigned", description="启运地")
    private String placeConsigned;

    /**
     * 动物类型(种类 0猪 1牛)
     */
    @Schema(name="animalType", description="动物类型(种类 0猪 1牛)")
    private Integer animalType;

    /**
     * 数量
     */
    @Schema(name="num", description="数量")
    private Integer num;

    /**
     * 单体重量
     */
    @Schema(name="monomerWeight", description="单体重量")
    private Float monomerWeight;

    /**
     * 取证照片
     */
    @Schema(name="photo", description="取证照片")
    private String photo;

    /**
     * 补充说明
     */
    @Schema(name="supplementaryNote", description="补充说明")
    private String supplementaryNote;

    /**
     * 签字内容
     */
    @Schema(name="sign", description="签字内容")
    private String sign;

    /**
     * 签字图片
     */
    @Schema(name="signUrl", description="签字图片")
    private String signUrl;

    /**
     * 签字时间
     */
    @Schema(name="signTime", description="签字时间")
    private String signTime;

    /**
     * 养殖户id
     */
    @Schema(name="userId", description="养殖户id")
    private Long userId;

    /**
     * 养殖户
     */
    @Schema(name="userName", description="养殖户")
    private String userName;

    /**
     * 养殖户头像
     */
    @Schema(name="userAvatarUrl", description="养殖户头像")
    private String userAvatarUrl;

    /**
     * 1:普通稽查(如果变3状态是该数据已被检测过下次不在显示),2:案件稽查(案件稽查数据一直可查看)
     */
    @Schema(name="status", description="1:普通稽查(第一次打开后将设置超时时间,超时后将删除该数据),2:案件稽查(案件稽查数据一直可查看)")
    private Integer status;


    /**
     * 超时时间
     */
    @Schema(name="overTime", description="超时时间")
    private Date overTime;


    /**
     * 创建时间
     */
    @Schema(name="createTime", description="创建时间")
    private Date createTime;


    /**
     * 修改时间
     */
    @Schema(name="updateTime", description="修改时间")
    private Date updateTime;


}
