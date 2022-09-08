package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hszn.nbmh.common.core.annotation.DataMasking;
import com.hszn.nbmh.common.core.enums.DataMaskingEnum;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户基本表
 * </p>
 *
 * @author yuan
 * @since 2022-08-15
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class NbmhUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value="id", type=IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 手机号码
     */
//    @DataMasking(maskEnum=DataMaskingEnum.HALF_MASK)
    private String phone;


    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 农牧币
     */
    private Integer coin;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 积分
     */
    private int integral;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 状态 0正常 -1冻结 -2离职
     */
    private Integer status;


}
