package com.hszn.nbmh.third.controller;

import com.baidu.aip.contentcensor.EImgType;
import com.hszn.nbmh.common.core.utils.Result;
import com.hszn.nbmh.common.security.annotation.Inner;
import com.hszn.nbmh.third.params.out.CensorResultOut;
import com.hszn.nbmh.third.service.AipContentCensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 百度内容审核接口
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-05 11:26
 */
@Slf4j
@RestController
@RequestMapping("/content_censor")
@Tag(name = "百度内容审核")
public class AipContentCensorController {
    private final AipContentCensorService contentCensorService;

    public AipContentCensorController(AipContentCensorService contentCensorService) {
        this.contentCensorService = contentCensorService;
    }

    /**
     * 参数为本地图片路径
     *
     * @param path
     * @return
     */
    @Operation(summary = "图片审核(参数为本地图片路径)")
    @Inner(false)
    @GetMapping("/imagePathDefined")
    public Result<CensorResultOut> imagePathDefined(@RequestParam("path") String path) {
        JSONObject jsonObject = contentCensorService.imageCensorUserDefined(path, EImgType.FILE, null);
        CensorResultOut result = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), CensorResultOut.class);
        return Result.ok(result);
    }

    /**
     * 参数为url
     *
     * @param url
     * @return
     */
    @Operation(summary = "图片审核(参数为url)")
    @Inner(false)
    @GetMapping("/imageUrlDefined")
    public Result<CensorResultOut> imageUrlDefined(@RequestParam("url") String url) {
        JSONObject jsonObject = contentCensorService.imageCensorUserDefined(url, EImgType.URL, null);
        CensorResultOut result = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), CensorResultOut.class);
        return Result.ok(result);
    }

    /**
     * 参数为文本
     *
     * @param text
     * @return
     */
    @Operation(summary = "文本审核")
    @Inner(false)
    @GetMapping("/textDefined")
    public Result<CensorResultOut> textDefined(@RequestParam("text") String text) {
        JSONObject jsonObject = contentCensorService.textCensorUserDefined(text);
        CensorResultOut result = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.toString(), CensorResultOut.class);
        return Result.ok(result);
    }
}
