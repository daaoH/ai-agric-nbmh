package com.hszn.nbmh.prevent.api.params.out;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/24
 * @Modified By:
 */
@Schema(name="防疫员管-防疫列表")
@Data
public class InspectRecordResult {
    /**
     * 养殖户id
     */
    @Schema(name="userId", description="养殖户id")
    private Long userId;

    /**
     * 养殖户
     */
    @Schema(name="userName", description="养殖户")
    private String userName;

    /**
     * 养殖户头像
     */
    @Schema(name="userAvatarUrl", description="养殖户头像")
    private String userAvatarUrl;

    /**
     * 养殖户手机号
     */
    @Schema(name="userPhone", description="养殖户手机号")
    private String userPhone;


    /**
     * 动物类型(种类 0猪 1牛)
     */
    @Schema(name="animalType", description="动物类型(种类 0猪 1牛)")
    private int animalType;

    /**
     * 检疫数量
     */
    @Schema(name="num", description="检疫数量")
    private int num;

    /**
     * 检疫时间
     */
    @Schema(name="inspectDate", description="检疫时间")
    private Date inspectDate;
}
