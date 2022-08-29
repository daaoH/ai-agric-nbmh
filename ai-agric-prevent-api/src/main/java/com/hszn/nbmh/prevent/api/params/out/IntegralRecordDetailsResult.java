package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/17
 * @Modified By:
 */
@Schema(name="防疫员管理详情-积分获取列表")
@Data
public class IntegralRecordDetailsResult implements Serializable {

    /**
     * 被邀请人
     */
    @Schema(name="userName", description="被邀请人")
    private String userName;

    /**
     * 防疫数量
     */
    @Schema(name="vaccinNum", description="防疫数量")
    private int vaccinNum;


    /**
     * 获取类型
     */
    @Schema(name="type", description="获取类型来源 1邀请 2动物防疫 3动物检疫)")
    private int type;


    /**
     * 动物类型
     */
    @Schema(name="animaltype", description="动物类型(种类 0猪 1牛)")
    private int animaltype;

    /**
     * 积分
     */
    @Schema(name="integral", description="积分")
    private int integral;






}
