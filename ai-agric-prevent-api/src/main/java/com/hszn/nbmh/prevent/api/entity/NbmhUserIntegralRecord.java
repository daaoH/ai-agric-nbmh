package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wangjun
 * @since 2022-08-22
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@TableName("nbmh_user_integral_record")
@Schema(name="积分记录信息")
public class NbmhUserIntegralRecord implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value="id", type=IdType.AUTO)
    @Schema(name="id", description="id")
    private Long id;

    /**
     * 农户id
     */
    @Schema(name="userId", description="农户id")
    private Long userId;

    /**
     * 农户名字
     */
    @Schema(name="userName", description="农户名字")
    private String userName;

    /**
     * 农户头像
     */
    @Schema(name="userAvatarUrl", description="农户头像")
    private String userAvatarUrl;

    /**
     * 防疫-检疫人-id
     */
    @Schema(name="vaccinId", description="防疫-检疫人-id")
    private Long vaccinId;

    /**
     * 防疫-检疫人
     */
    @Schema(name="vaccinUser", description="防疫-检疫人")
    private String vaccinUser;

    /**
     * 防疫检疫员头像
     */
    @Schema(name="vaccinAvatarUrl", description="防疫检疫员头像")
    private String vaccinAvatarUrl;

    /**
     * 动物id
     */
    @Schema(name="animalId", description="动物id")
    private Long animalId;

    /**
     * 动物类型(种类 0猪 1牛)
     */
    @Schema(name="animalType", description="动物类型(种类 0猪 1牛)")
    private Integer animalType;

    /**
     * 动物耳标号
     */
    @Schema(name="earNo", description="耳标号")
    private String earNo;

    /**
     * 来源(1:防疫,2:检疫,3:分账)
     */
    @Schema(name="source", description="来源(1:邀请 2:防疫,3:检疫,3:分账)")
    private Integer source;

    /**
     * 是否为收入(0:false,1:true)
     */
    @Schema(name="isIncome", description="是否为收入(0:false,1:true)")
    private int isIncome;

    /**
     * 积分
     */
    @Schema(name="integral", description="积分")
    private int integral;


    /**
     * 商品id
     */
    @Schema(name="goodsId", description="商品id")
    private Long goodsId;
    /**
     * 积分
     */
    @Schema(name="goodsName", description="商品名")
    private String goodsName;

    /**
     * 状态
     */
    @Schema(name="status", description="状态")
    private int status;

    /**
     * 添加时间
     */
    @Schema(name="createTime", description="添加时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @Schema(name="updateTime", description="修改时间")
    private Date updateTime;

    /**
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private Long preventStationId;


}
