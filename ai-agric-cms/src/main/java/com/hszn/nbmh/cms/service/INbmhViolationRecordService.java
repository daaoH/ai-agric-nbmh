package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.cms.api.entity.NbmhRuleExplain;
import com.hszn.nbmh.cms.api.entity.NbmhViolationRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.common.core.mould.QueryRequest;

/**
 * <p>
 * 违规记录表 服务类
 * </p>
 *
 * @author 李肖
 * @since 2022-09-03
 */
public interface INbmhViolationRecordService extends IService<NbmhViolationRecord> {
    IPage<NbmhViolationRecord> getByPage(QueryRequest<NbmhViolationRecord> nbmhViolationRecord);
}
