package com.hszn.nbmh.third.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.third.entity.NbmhBaseConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 全局配置表 服务类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-26
 */
public interface INbmhBaseConfigService extends IService<NbmhBaseConfig> {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhBaseConfig>
     */
    IPage<NbmhBaseConfig> getByPage(QueryRequest<NbmhBaseConfig> param);


    /**
     * 获取对象信息(全条件)
     *
     * @param subject
     * @return NbmhBaseConfig
     */
    List<NbmhBaseConfig> getBySubject(String subject);

}
