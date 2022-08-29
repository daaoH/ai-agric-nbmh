package com.hszn.nbmh.prevent.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/25
 * @Modified By:
 */

@Data
@Schema(name="稽查输入参数信息")
public class CheckParam {

    /**
     * 稽查员id
     */
    @Schema(name="checkId", description="稽查员id")
    private Long checkId;

    /**
     * 状态(1普通稽查2案件稽查)
     */
    @Schema(name="status", description="状态(1:普通稽查2:案件稽查)")
    private Integer status;

}
