package com.hszn.nbmh.shop.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hszn.nbmh.shop.api.entity.NbmhShopExtraInfo;
import com.hszn.nbmh.shop.mapper.NbmhShopExtraInfoMapper;
import com.hszn.nbmh.shop.service.INbmhShopExtraInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 店铺扩展信息表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2022-09-02
 */
@Slf4j
@Service("shopExtraInfoService")
@Transactional(rollbackFor = Exception.class)
public class NbmhShopExtraInfoServiceImpl extends ServiceImpl<NbmhShopExtraInfoMapper, NbmhShopExtraInfo> implements INbmhShopExtraInfoService {

    @Override
    public NbmhShopExtraInfo finByShopId(Long shopId) {
        LambdaQueryChainWrapper<NbmhShopExtraInfo> wrapper = lambdaQuery().eq(NbmhShopExtraInfo::getShopId, shopId);
        final NbmhShopExtraInfo result = this.getOne(wrapper);
        return result;
    }
}
