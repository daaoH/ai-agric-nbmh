package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户凭证表
 * </p>
 *
 * @author yuan
 * @since 2022-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NbmhUserCredentials implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名或手机号等
     */
    @TableField("user_name")
    private String userName;

    /**
     * 账号类型（用户名、手机号）
     */
    private Integer type;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;


}
