package com.hszn.nbmh.marketing.service.impl;

import com.hszn.nbmh.marketing.api.entity.NbmhSeckill;
import com.hszn.nbmh.marketing.mapper.NbmhSeckillMapper;
import com.hszn.nbmh.marketing.service.INbmhSeckillService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-09-08
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhSeckillServiceImpl extends ServiceImpl<NbmhSeckillMapper, NbmhSeckill> implements INbmhSeckillService {
    @Resource
    private NbmhSeckillMapper nbmhSeckillMapper;



}
