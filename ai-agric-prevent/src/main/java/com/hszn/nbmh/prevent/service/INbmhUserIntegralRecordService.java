package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhUserIntegralRecord;
import com.hszn.nbmh.prevent.api.params.input.UserIntegralRecordParam;
import com.hszn.nbmh.prevent.api.params.out.IntegralRecordDetailsResult;
import com.hszn.nbmh.prevent.api.params.out.IntegralRecordInfoResult;
import com.hszn.nbmh.prevent.api.params.out.VUserIntegralRecordResult;

import java.util.List;

/**
 * <p>
 * 积分记录信息 服务类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-22
 */
public interface INbmhUserIntegralRecordService extends IService<NbmhUserIntegralRecord> {

    /**
     * 防疫员管理详情-积分获取列表
     *
     * @param param
     * @return List<NbmhUserIntegralRecord>
     */
    List<IntegralRecordDetailsResult> integralRecordDetails(UserIntegralRecordParam param);


    /**
     * 积分记录
     *
     * @param param
     * @return List<IntegralRecordResult>
     */
    IntegralRecordInfoResult integralRecord(UserIntegralRecordParam param);


    /**
     * 防疫员积分获取记录
     *
     * @param param
     * @return List<VUserIntegralRecordResult>
     */
    VUserIntegralRecordResult vUserIntegralRecordResult(UserIntegralRecordParam param);

}
