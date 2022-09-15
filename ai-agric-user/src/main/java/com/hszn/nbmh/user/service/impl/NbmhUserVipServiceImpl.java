package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.NbmhUserVip;
import com.hszn.nbmh.user.mapper.NbmhUserVipMapper;
import com.hszn.nbmh.user.service.INbmhUserVipService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 用户vip价格绑定表 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhUserVipServiceImpl extends ServiceImpl<NbmhUserVipMapper, NbmhUserVip> implements INbmhUserVipService {
    @Resource
    private NbmhUserVipMapper nbmhUserVipMapper;



}
