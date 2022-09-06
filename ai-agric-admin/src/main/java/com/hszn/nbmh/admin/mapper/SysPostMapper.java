package com.hszn.nbmh.admin.mapper;

import com.hszn.nbmh.admin.api.entity.SysPost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 岗位信息表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2022-09-06
 */
public interface SysPostMapper extends BaseMapper<SysPost> {

    /**
     * 通过用户ID，查询岗位信息
     * @param userId 用户id
     * @return 岗位信息
     */
    List<SysPost> listPostsByUserId(Long userId);
}
