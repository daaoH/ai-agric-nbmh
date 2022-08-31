package com.hszn.nbmh.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.user.api.entity.NbmhAnimalDoctorDetail;
import com.hszn.nbmh.user.mapper.NbmhAnimalDoctorDetailMapper;
import com.hszn.nbmh.user.service.INbmhAnimalDoctorDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 兽医详细信息表 服务实现类
 * </p>
 *
 * @author MCR
 * @since 2022-08-30
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhAnimalDoctorDetailServiceImpl extends ServiceImpl<NbmhAnimalDoctorDetailMapper, NbmhAnimalDoctorDetail> implements INbmhAnimalDoctorDetailService {
    @Resource
    private NbmhAnimalDoctorDetailMapper nbmhAnimalDoctorDetailMapper;



}
