package com.hszn.nbmh.shop.service.impl;

import com.hszn.nbmh.shop.api.entity.NbmhFreightTemplate;
import com.hszn.nbmh.shop.mapper.NbmhFreightTemplateMapper;
import com.hszn.nbmh.shop.service.INbmhFreightTemplateService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 运费模板 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhFreightTemplateServiceImpl extends ServiceImpl<NbmhFreightTemplateMapper, NbmhFreightTemplate> implements INbmhFreightTemplateService {
    @Resource
    private NbmhFreightTemplateMapper nbmhFreightTemplateMapper;



}
