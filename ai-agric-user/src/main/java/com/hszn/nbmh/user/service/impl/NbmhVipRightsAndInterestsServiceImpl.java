package com.hszn.nbmh.user.service.impl;

import com.hszn.nbmh.user.api.entity.NbmhVipRightsAndInterests;
import com.hszn.nbmh.user.mapper.NbmhVipRightsAndInterestsMapper;
import com.hszn.nbmh.user.service.INbmhVipRightsAndInterestsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 * vip用户权益 服务实现类
 * </p>
 *
 * @author wangjun
 * @since 2022-09-15
 */
@Slf4j
@Service
@Transactional(rollbackFor=Exception.class)
public class NbmhVipRightsAndInterestsServiceImpl extends ServiceImpl<NbmhVipRightsAndInterestsMapper, NbmhVipRightsAndInterests> implements INbmhVipRightsAndInterestsService {


}
