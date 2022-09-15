package com.hszn.nbmh.prevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.prevent.api.entity.NbmhLibraryCategory;
import com.hszn.nbmh.prevent.mapper.NbmhLibraryCategoryMapper;
import com.hszn.nbmh.prevent.service.INbmhLibraryCategoryService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 知识库分类服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-09-14
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhLibraryCategoryServiceImpl extends ServiceImpl<NbmhLibraryCategoryMapper, NbmhLibraryCategory> implements INbmhLibraryCategoryService {

    @Override
    public List<NbmhLibraryCategory> search(NbmhLibraryCategory param) {
        List<NbmhLibraryCategory> list = findByParentId(-1L);
        list.forEach(e -> e.setChild(findByParentId(e.getId())));
        return list;
    }

    public List<NbmhLibraryCategory> findByParentId(Long parentId) {
        LambdaQueryWrapper<NbmhLibraryCategory> wrapper = Wrappers.lambdaQuery();
        return list(wrapper.eq(NbmhLibraryCategory::getParentId, parentId));
    }
}
