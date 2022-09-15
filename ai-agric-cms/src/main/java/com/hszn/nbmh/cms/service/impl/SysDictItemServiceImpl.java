package com.hszn.nbmh.cms.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.cms.api.entity.SysDictItem;
import com.hszn.nbmh.cms.mapper.SysDictItemMapper;
import com.hszn.nbmh.cms.service.ISysDictItemService;
import com.hszn.nbmh.common.core.mould.QueryRequest;
import com.hszn.nbmh.common.core.utils.SortUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 字典项 服务实现类
 * </p>
 *
 * @author 李肖
 * @since 2022-08-31
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements ISysDictItemService {

    public IPage<SysDictItem>getByPage(QueryRequest<SysDictItem> param) {
        //添加条件
        LambdaQueryWrapper<SysDictItem> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtils.isNotEmpty(param.getQueryEntity())) {
            //id
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getId())) {
                queryWrapper.eq(SysDictItem::getId, param.getQueryEntity().getId());
            }
            //状态
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getStatus())) {
                queryWrapper.eq(SysDictItem::getStatus, param.getQueryEntity().getStatus());
            }
            //农户8
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getType())) {
                queryWrapper.eq(SysDictItem::getType, param.getQueryEntity().getType());
            }
            //时间
            if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                //时间 查询条件为年月日匹配
                if (ObjectUtils.isNotEmpty(param.getQueryEntity().getCreateTime())) {
                    String strStart = DateFormatUtils.format(param.getQueryEntity().getCreateTime(), "yyyy-MM-dd");
                    queryWrapper.apply("date_format(create_time,'%Y-%m-%d) = date_format('" + strStart + "','%Y-%m-%d')");
                }
            }
        }
        //分页
        Page<SysDictItem> page = new Page<>(param.getPageNum(), param.getPageSize());
        //排序
        SortUtil.handlePageSort(param, page, param.getField(), param.getOrder(), true);
        return baseMapper.selectPage(page, queryWrapper);
    }
}
