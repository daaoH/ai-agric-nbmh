package com.hszn.nbmh.good.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hszn.nbmh.good.api.entity.NbmhGoodsSpecification;
import com.hszn.nbmh.good.api.params.vo.SpecsVo;
import com.hszn.nbmh.good.mapper.NbmhGoodsSpecificationMapper;
import com.hszn.nbmh.good.service.INbmhGoodsSpecificationService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品规格表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhGoodsSpecificationServiceImpl extends ServiceImpl<NbmhGoodsSpecificationMapper, NbmhGoodsSpecification> implements INbmhGoodsSpecificationService {
    @Resource
    private NbmhGoodsSpecificationMapper nbmhGoodsSpecificationMapper;

}
