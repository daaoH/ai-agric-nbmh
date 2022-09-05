package com.hszn.nbmh.third.service.impl;

import com.baidu.aip.contentcensor.AipContentCensor;
import com.baidu.aip.contentcensor.EImgType;
import com.hszn.nbmh.third.service.AipContentCensorService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度内容审核服务实现类
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-05 11:26
 */
@Service
public class AipContentCensorServiceImpl implements AipContentCensorService {
    private final AipContentCensor client;

    public AipContentCensorServiceImpl(AipContentCensor client) {
        this.client = client;
    }

    @Override
    public JSONObject imageCensorUserDefined(String image, EImgType type, HashMap<String, Object> options) {
        return client.imageCensorUserDefined(image, type, options);
    }

    @Override
    public JSONObject textCensorUserDefined(String text) {
        return client.textCensorUserDefined(text);
    }
}
