package com.hszn.nbmh.prevent.api.params.out;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/09/09
 * @Modified By:
 */
@Schema(name="站长 防疫统计 组装对象")
@Data
public class MapAndDataResult {

    @Schema(name="name", description="顶部数据名称")
    private String name;

    @Schema(name="data", description="数值集合列表")
    private List<Integer> data;

}
