package com.hszn.nbmh.third.mapper;

import com.hszn.nbmh.third.entity.NbmhSignatureInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 签名信息表 Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2022-09-08
 */
public interface NbmhSignatureInfoMapper extends BaseMapper<NbmhSignatureInfo> {

    void updateStatus(@Param("flowId") String flowId, @Param("status") int status, @Param("url") String url);
}
