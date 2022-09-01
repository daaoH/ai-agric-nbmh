package com.hszn.nbmh.prevent.api.params.out;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/30
 * @Modified By:
 */
@Schema(name="防疫员管-防疫列表")
@Data
public class VaccinResult {
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
     * 养殖地址
     */
    @Schema(name="userAddress", description="养殖户头像")
    private String userAddress;

    /**
     * 养殖电话
     */
    @Schema(name="userAvatarUrl", description="养殖电话")
    private String userPhone;

    /**
     * 防疫时间
     */
    @Schema(name="vaccinDate", description="防疫时间")
    private Date vaccinDate;


    /**
     * 动物类型(种类 0猪 1牛)
     */
    @Schema(name="animalType", description="动物类型(种类 0猪 1牛)")
    private int animalType;

    /**
     * 身份证号
     */
    @Schema(name="farmerIdNo", description="身份证号")
    private String farmerIdNo;


    /**
     * 总数量
     */
    @Schema(name="totalNum", description="总数量")
    private int totalNum;


    /**
     * 已免疫数量
     */
    @Schema(name="immunizedNum", description="已免疫数量")
    private int immunizedNum;


    /**
     * 未免疫数量
     */
    @Schema(name="notImmune", description="未免疫数量")
    private int notImmune;


    /**
     * 疫苗种类
     */
    @Schema(name="vaccines", description="疫苗种类")
    private List<String> vaccines;


    /**
     * 防疫人员
     */
    @Schema(name="preventPersonnel", description="防疫人员")
    private List<String> preventPersonnel;

    /**
     * 防疫动物列表
     */
    @Schema(name="animalResultList", description="防疫动物列表")
    private List<AnimalResult> animalResultList;

}
