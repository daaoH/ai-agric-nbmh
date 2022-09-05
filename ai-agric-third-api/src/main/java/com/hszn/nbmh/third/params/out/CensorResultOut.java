package com.hszn.nbmh.third.params.out;

import com.fasterxml.jackson.annotation.JsonAlias;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 图片审核返回结果集
 *
 * @author liwei
 * @version 1.0
 * @since 2022-09-05 11:39
 */
@Data
@Schema(name = "CensorResultOut", description = "审核返回结果集")
public class CensorResultOut {
    @Schema(name = "logId", description = "请求唯一id，用于问题排查")
    @JsonAlias("log_id")
    private Long logId;
    @Schema(name = "errorCode", description = "错误提示码，失败才返回，成功不返回")
    @JsonAlias("error_code")
    private Long errorCode;
    @Schema(name = "errorMsg", description = "错误提示信息，失败才返回，成功不返回")
    @JsonAlias("error_msg")
    private String errorMsg;
    @Schema(name = "conclusion", description = "审核结果，可取值描述：合规、不合规、疑似、审核失败")
    private String conclusion;
    @Schema(name = "conclusionType", description = "审核结果类型，可取值1、2、3、4，分别代表1：合规，2：不合规，3：疑似，4：审核失败")
    private Integer conclusionType;
    @Schema(name = "data", description = "不合规/疑似/命中白名单项详细信息。响应成功并且conclusion为疑似或不合规或命中白名单时才返回，响应失败或conclusion为合规且未命中白名单时不返回。")
    private List<DataInfo> data;

    @Data
    public static class DataInfo {
        @Schema(name = "msg", description = "不合规项描述信息")
        private String msg;
        @Schema(name = "type", description = "原生结果的类型1：色情识别、2：暴恐识别、3：恶心图识别、4:广告监测、5：政治敏感识别、6：图像质量检测、7：用户图像黑名单、8：用户图像白名单、9：图文审核")
        private Integer type;
    }


}
