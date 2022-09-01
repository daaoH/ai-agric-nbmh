package com.hszn.nbmh.shop.api.params.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 开店经营类目表
 * </p>
 *
 * @author lw
 * @since 2022-09-01
 */
@Data
@Schema(description = "经营类目")
public class OperateCategoryParam{

    /**
     * 父ID
     */
    @Schema(description = "父级ID")
    private Long parentId;

    /**
     * 类目名称
     */
    @Schema(description = "名字,模糊查询")
    private String name;

}
