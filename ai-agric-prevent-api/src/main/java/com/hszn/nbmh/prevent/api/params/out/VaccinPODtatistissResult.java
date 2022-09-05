package com.hszn.nbmh.prevent.api.params.out;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.prevent.api.entity.NbmhCheck;
import com.hszn.nbmh.prevent.api.entity.NbmhInspect;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import com.hszn.nbmh.user.api.params.out.CurUserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/30
 * @Modified By:
 */
@Schema(name="防疫员管-防疫列表")
@Data
public class VaccinPODtatistissResult {
    /**
     * 积分
     */
    @Schema(name="integralNum", description="积分")
    private int integralNum;

    /**
     * 农牧币
     */
    @Schema(name="coinNum", description="农牧币")
    private Integer coinNum;

    /**
     * 防疫数量
     */
    @Schema(name="vaccinNum", description="防疫数量")
    private Long vaccinNum;

    /**
     * 检疫数量
     */
    @Schema(name="inspectNum", description="检疫数量")
    private Long inspectNum;


    /**
     * 举报数量
     */
    @Schema(name="checkNum", description="举报数量")
    private Long checkNum;
}
