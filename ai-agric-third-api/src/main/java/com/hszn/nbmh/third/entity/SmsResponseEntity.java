package com.hszn.nbmh.third.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 短信发送返回信息实体
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
public class SmsResponseEntity {
    /**
     *短信发送结果
     */
    private boolean sendResult;

    /**
     *短信发送结果提示
     */
    private String msg;
}
