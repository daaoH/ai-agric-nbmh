package com.hszn.nbmh.good.service.impl;

import com.hszn.nbmh.good.api.entity.NbmhGoodsAttribute;
import com.hszn.nbmh.good.mapper.NbmhGoodsAttributeMapper;
import com.hszn.nbmh.good.service.INbmhGoodsAttributeService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 商品参数表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhGoodsAttributeServiceImpl extends ServiceImpl<NbmhGoodsAttributeMapper, NbmhGoodsAttribute> implements INbmhGoodsAttributeService {
    @Resource
    private NbmhGoodsAttributeMapper nbmhGoodsAttributeMapper;



}
