package com.hszn.nbmh.prevent.api.params.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/23
 * @Modified By:
 */
@Schema(name="防疫员管理详情-积分获取列表对象")
@Data
public class VUserIntegralRecordListResult {

    /**
     * 被邀请人(农户名字)
     */
    @Schema(name="userName", description="农户名字")
    private String userName;

    /**
     * 农户头像
     */
    @Schema(name="userAvatarUrl", description="农户头像")
    private String userAvatarUrl;

    /**
     * 防疫数量
     */
    @Schema(name="vaccinNum", description="防疫数量")
    private int vaccinNum;

    /**
     * 积分
     */
    @Schema(name="integral", description="积分")
    private int integral;


    /**
     * 动物类型
     */
    @Schema(name="earNos", description="耳标集合")
    private List<String> earNos;

}
