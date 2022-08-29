package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.prevent.api.entity.NbmhInspect;
import com.hszn.nbmh.prevent.api.params.input.InspectRecordParam;
import com.hszn.nbmh.prevent.api.params.out.InspectRecordDetailsResult;
import com.hszn.nbmh.prevent.api.params.out.InspectRecordResult;

import java.util.List;

/**
 * <p>
 * 检疫 检擦 服务类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-24
 */
public interface INbmhInspectService extends IService<NbmhInspect> {

    /**
     * 根据耳标获取数据对象
     *
     * @param earNo
     * @return
     */
    NbmhInspect getByEarNo(String earNo);


    /**
     *
     */
    /**
     * 防疫员 防疫记录
     *
     * @param inspectRecordParam
     * @return
     */
    List<InspectRecordResult> record(InspectRecordParam inspectRecordParam);


    /**
     *
     */
    /**
     * 防疫员 防疫记录详情
     *
     * @param inspectRecordParam
     * @return
     */
    InspectRecordDetailsResult recordDetails(InspectRecordParam inspectRecordParam);

}
