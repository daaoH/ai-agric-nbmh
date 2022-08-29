package com.hszn.nbmh.prevent.api.params.out;

import com.hszn.nbmh.prevent.api.entity.NbmhInspect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/24
 * @Modified By:
 */
@Schema(name="防疫员管-防疫记录详情")
@Data
public class InspectRecordDetailsResult extends NbmhInspect {

    /**
     * 交易数量
     */
    @Schema(name="num", description="交易数量")
    private int num;

    /**
     * 耳标集合
     */
    @Schema(name="userId", description="耳标集合")
    private List<String> earNos;

}
