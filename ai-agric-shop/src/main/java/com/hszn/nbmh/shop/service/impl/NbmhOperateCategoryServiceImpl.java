package com.hszn.nbmh.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.shop.api.entity.NbmhOperateCategory;
import com.hszn.nbmh.shop.api.params.input.OperateCategoryParam;
import com.hszn.nbmh.shop.mapper.NbmhOperateCategoryMapper;
import com.hszn.nbmh.shop.service.INbmhOperateCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 开店经营类目表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-09-01
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhOperateCategoryServiceImpl extends ServiceImpl<NbmhOperateCategoryMapper, NbmhOperateCategory> implements INbmhOperateCategoryService {

    @Override
    public List<NbmhOperateCategory> search(OperateCategoryParam param) {
        List<NbmhOperateCategory> list = findByParentId(-1L);
        list.forEach(e -> e.setChild(findByParentId(e.getId())));
        return list;
    }

    public List<NbmhOperateCategory> findByParentId(Long parentId) {
        LambdaQueryWrapper<NbmhOperateCategory> wrapper = Wrappers.lambdaQuery();
        return list(wrapper.eq(NbmhOperateCategory::getParentId, parentId));
    }
}
