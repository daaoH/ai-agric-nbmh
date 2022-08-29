package com.hszn.nbmh.common.core.mould;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author DaWang
 */
@Data
@ToString
@Schema(name="分页参数对象")
public class QueryRequest<T> implements Serializable {

    private static final long serialVersionUID=-4869594085374385813L;

    @Schema(name="pageSize", description="当前页面数据量")
    private int pageSize=10;

    @Schema(name="pageNum", description="当前页码")
    private int pageNum=1;

    @Schema(name="field", description="排序字段")
    private String field;

    @Schema(name="order", description="排序规则，ascending=asc升序，descending= desc降序")
    private String order;

    @Schema(name="queryEntity", description="查询条件对象(可以是对应实体对象)")
    private T queryEntity;

}
