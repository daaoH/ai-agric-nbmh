package com.hszn.nbmh.third.service;

import com.baidu.aip.contentcensor.EImgType;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 百度内容审核服务类
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-05 11:25
 */
public interface AipContentCensorService {

    /**
     * 图片审核
     *
     * @param image
     * @param type
     * @param options
     * @return
     */
    JSONObject imageCensorUserDefined(String image, EImgType type, HashMap<String, Object> options);

    /**
     * 文本内容审核
     *
     * @param text
     * @return
     */
    JSONObject textCensorUserDefined(String text);
}
