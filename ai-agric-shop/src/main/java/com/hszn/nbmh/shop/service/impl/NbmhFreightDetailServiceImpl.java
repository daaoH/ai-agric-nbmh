package com.hszn.nbmh.shop.service.impl;

import com.hszn.nbmh.shop.api.entity.NbmhFreightDetail;
import com.hszn.nbmh.shop.mapper.NbmhFreightDetailMapper;
import com.hszn.nbmh.shop.service.INbmhFreightDetailService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 运费模板详细信息 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhFreightDetailServiceImpl extends ServiceImpl<NbmhFreightDetailMapper, NbmhFreightDetail> implements INbmhFreightDetailService {
    @Resource
    private NbmhFreightDetailMapper nbmhFreightDetailMapper;



}
