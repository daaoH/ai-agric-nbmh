package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.NbmhUserFootprint;
import com.hszn.nbmh.user.mapper.NbmhUserFootprintMapper;
import com.hszn.nbmh.user.service.INbmhUserFootprintService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 用户浏览足迹表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhUserFootprintServiceImpl extends ServiceImpl<NbmhUserFootprintMapper, NbmhUserFootprint> implements INbmhUserFootprintService {
    @Resource
    private NbmhUserFootprintMapper nbmhUserFootprintMapper;



}
