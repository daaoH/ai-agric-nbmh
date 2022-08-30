package com.hszn.nbmh.prevent.api.params.out;


import com.hszn.nbmh.common.core.utils.PageModelUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/30
 * @Modified By:
 */
@Schema(name="防疫员管-防疫列表")
@Data
public class VaccinPageResult {
    /**
     * 防疫记录分页结果集
     */
    @Schema(name="pageModel", description="防疫记录分页结果集")
    private PageModelUtils pageModel;


    /**
     * 年防疫总数量
     */
    @Schema(name="yearTotalNum", description="年防疫总数量")
    private Long yearTotalNum;

}
