package com.hszn.nbmh.cms.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.cms.api.entity.SysDictItem;
import com.hszn.nbmh.common.core.mould.QueryRequest;

/**
 * <p>
 * 字典项 服务类
 * </p>
 *
 * @author 李肖
 * @since 2022-08-31
 */
public interface ISysDictItemService extends IService<SysDictItem> {
    IPage<SysDictItem> getByPage(QueryRequest<SysDictItem> sysDictItem);
}
