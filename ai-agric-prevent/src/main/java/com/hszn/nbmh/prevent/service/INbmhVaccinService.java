package com.hszn.nbmh.prevent.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;

import java.util.List;

/**
 * <p>
 * 防疫信息表 服务类
 * </p>
 *
 * @author wangjun
 * @since 2022-08-16
 */
public interface INbmhVaccinService extends IService<NbmhVaccin> {

    /**
     * 分页查询
     *
     * @param param
     * @return IPage<NbmhVaccin>
     */
    IPage<NbmhVaccin> getByPage(QueryRequest<NbmhVaccin> param);


    /**
     * 分页查询
     *
     * @param earNo
     * @return list
     */
    List<NbmhVaccin> getByEarNo(String earNo);

    /**
     * 防疫动物详情
     *
     * @param vaccin
     * @return List<NbmhVaccin>
     */
    List<NbmhVaccin> details(NbmhVaccin vaccin);


    /**
     * @param param(根据防疫人,动物类型,时间,获取历史具体防疫动物数量)
     * @return num
     * 防疫数据统计数量
     */
    Long getNum(NbmhVaccin param);


}
