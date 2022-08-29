package com.hszn.nbmh.good.service.impl;

import com.hszn.nbmh.good.api.entity.NbmhComment;
import com.hszn.nbmh.good.mapper.NbmhCommentMapper;
import com.hszn.nbmh.good.service.INbmhCommentService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author yuan
 * @since 2022-08-25
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class NbmhCommentServiceImpl extends ServiceImpl<NbmhCommentMapper, NbmhComment> implements INbmhCommentService {
    @Resource
    private NbmhCommentMapper nbmhCommentMapper;



}
