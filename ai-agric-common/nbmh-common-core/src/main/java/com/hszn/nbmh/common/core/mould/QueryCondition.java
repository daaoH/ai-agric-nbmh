package com.hszn.nbmh.common.core.mould;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(description = "查询条件")
public class QueryCondition<T> implements Serializable {

    /**
     * 查询用到的实体
     */
    @ApiModelProperty(value = "查询用到的实体")
    T entity;

    /**
     * 排序方式
     */
    @ApiModelProperty(value = "排序方式")
    List<OrderItem> orderItemList;
}
