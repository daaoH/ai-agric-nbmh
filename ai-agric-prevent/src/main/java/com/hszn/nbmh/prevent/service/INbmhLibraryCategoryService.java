package com.hszn.nbmh.prevent.service;

import com.hszn.nbmh.prevent.api.entity.NbmhLibraryCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 知识库分类服务类
 * </p>
 *
 * @author lw
 * @since 2022-09-14
 */
public interface INbmhLibraryCategoryService extends IService<NbmhLibraryCategory> {

    /**
     * 查询接口
     *
     * @param param
     * @return
     */
    List<NbmhLibraryCategory> search(NbmhLibraryCategory param);
}
