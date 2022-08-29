package com.hszn.nbmh.good.api.params.vo;

import com.hszn.nbmh.good.api.entity.NbmhComment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午3:34 22/8/26
 * @Modified By:
 */
@Schema(description = "评论数据")
@Data
public class CommentVo {

    @Schema(description = "评论数")
    private Long total;

    @Schema(description = "评论数据")
    private List<NbmhComment> comment;
}
