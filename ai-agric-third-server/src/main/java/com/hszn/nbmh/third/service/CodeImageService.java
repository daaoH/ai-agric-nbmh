package com.hszn.nbmh.third.service;


import com.hszn.nbmh.common.core.mould.CodeImageRequest;

public interface CodeImageService {

    /**
     * 生成二维码
     *
     * @param param(生成二维码参数)
     * @return str
     */
    String generate(CodeImageRequest param);
}
