package com.hszn.nbmh.third.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 短信验证码验证结果实体
 * </p>
 *
 * @author MCR
 * @since 2022-08-19
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SmsValidateEntity implements Serializable {
    /**
     *短信验证码验证结果
     */
    private boolean validateResult;

    /**
     *短信验证码验证结果提示
     */
    private String msg;
}
