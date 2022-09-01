package com.hszn.nbmh.order.mapper;

import com.hszn.nbmh.order.api.entity.NbmhOrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单中所包含的商品 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2022-09-01
 */
public interface NbmhOrderItemMapper extends BaseMapper<NbmhOrderItem> {

    /**
     * 根据用户Id及name获取明细
     *
     * @param name
     * @param userId
     * @return list
     */
    List<NbmhOrderItem> findByNmAndUid(@Param("name") String name, @Param("userId") String userId);
}
