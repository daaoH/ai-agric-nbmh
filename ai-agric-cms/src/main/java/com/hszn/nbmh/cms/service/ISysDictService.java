package com.hszn.nbmh.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.cms.api.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.common.core.mould.QueryRequest;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author 李肖
 * @since 2022-08-31
 */
public interface ISysDictService extends IService<SysDict> {

    /**
     * 分页查询
     *
     * @param sysDict
     * @return IPage
     */
    IPage<SysDict> getByPage(QueryRequest<SysDict> sysDict);
}