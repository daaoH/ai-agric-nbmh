package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.hszn.nbmh.cms.api.entity.NbmhRuleExplain;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.cms.api.entity.SysDict;
import com.hszn.nbmh.cms.api.entity.SysDictItem;
import com.hszn.nbmh.common.core.mould.QueryRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 规则说明表 服务类
 * </p>
 *
 * @author 李肖
 * @since 2022-09-02
 */
public interface INbmhRuleExplainService extends IService<NbmhRuleExplain> {
    IPage<NbmhRuleExplain> getByPage(QueryRequest<NbmhRuleExplain> nbmhRuleExplain);
}
