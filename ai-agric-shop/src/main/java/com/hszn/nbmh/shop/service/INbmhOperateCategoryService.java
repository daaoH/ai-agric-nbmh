package com.hszn.nbmh.shop.service;

import com.hszn.nbmh.shop.api.entity.NbmhOperateCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hszn.nbmh.shop.api.params.input.OperateCategoryParam;

import java.util.List;

/**
 * <p>
 * 开店经营类目表 服务类
 * </p>
 *
 * @author lw
 * @since 2022-09-01
 */
public interface INbmhOperateCategoryService extends IService<NbmhOperateCategory> {

    /**
     * 查询接口
     * @param param
     * @return
     */
    List<NbmhOperateCategory> search(OperateCategoryParam param);
}
