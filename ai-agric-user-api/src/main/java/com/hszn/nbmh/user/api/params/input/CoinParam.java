package com.hszn.nbmh.user.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * @Author：wangjun
 * @Description: 农牧币请求参数
 * @Date: 22/09/14
 * @Modified
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "农牧币请求参数")
public class CoinParam {

    /**
     * 用户id
     */
    @Schema(name = "userId", description = "用户id")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 变更类型（0：扣减；1：增加）不能为空
     */
    @NotNull(message = "变更类型为空")
    @Schema(name = "payType", description = "变更类型（0：扣减；1：增加）不能为空")
    private Integer payType;

    /**
     * 变更金额
     */
    @NotNull(message = "变更金额不能为空")
    @Schema(name = "payMoney", description = "变更金额")
    private BigDecimal payMoney;


    /**
     * 业务id
     */
    @NotNull(message = "业务id为空")
    private Long bizId;

    /**
     * 业务类型
     */
    @Schema(name = "bizType", description = "业务类型")
    @NotBlank(message = "业务类型为空")
    private String bizType;


}
