package com.hszn.nbmh.cms.api.params.out;

import com.hszn.nbmh.cms.api.entity.SysDict;
import com.hszn.nbmh.cms.api.entity.SysDictItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 字典返回结果信息返回结果集
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-14
 */
@Data
@Schema(name = "CensorResultOut", description = "字典返回结果信息")
public class DictResultOut {

    @Schema(name = "dict", description = "字典数据")
    private SysDict dict;

    @Schema(name = "dict", description = "字典数据")
    private List<SysDictItem> dictItemList;

}
