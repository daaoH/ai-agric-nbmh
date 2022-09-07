package com.hszn.nbmh.prevent.api.params.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hszn.nbmh.prevent.api.entity.NbmhVaccin;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author：wangjun
 * @Description:
 * @Date:上午20:15 22/8/16
 * @Modified By:
 */
@Schema(name="防疫请求参数信息")
@Data
public class VaccinParam extends NbmhVaccin implements Serializable {

    /**
     * defaultValue = "javalover", example = "javalover" =例子
     * 农畜耳标号
     */
    @Schema(name="earNo", description="耳标号")
    private String earNo;

    /**
     * 动物所属农场id
     */
    @Schema(name="farmId", description="动物所属农场id")
    private Long farmId;

    /**
     * 农畜种类( 0猪 1牛)
     */
    @Schema(name="type", description="0猪 1牛")
    private int type;

    /**
     * 品种
     */
    @Schema(name="category", description="品种")
    private String category;

    /**
     * 月龄
     */
    @Schema(name="age", description="月龄")
    private int age;

    /**
     * 重量
     */
    @Schema(name="weight", description="重量")
    private float weight;

    /**
     * 是否参加农险(0否 1是)
     */
    @Schema(name="insured", description="0否 1是")
    private int insured;

    /**
     * 农险证明(图片)
     */
    @Schema(name="insurePic", description="农险证明(图片)")
    private String insurePic;

    /**
     * 农户id
     */
    @Schema(name="userId", description="农户id")
    private Long userId;

    /**
     * 农户手机号
     */
    @Schema(name="userPhone", description="农户手机号")
    private String userPhone;

    /**
     * 农畜图片
     */
    @Schema(name="photos", description="农畜图片")
    private String photos;

    /**
     * 二维码
     */
    @Schema(name="qrcode", description="二维码")
    private String qrcode;

    /**
     * 备注
     */
    @Schema(name="remark", description="备注")
    private String remark;

    /**
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private Long preventStationId;


    /**
     * 防疫时间
     */
    @Schema(name="vaccinDate", description="防疫时间")

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date vaccinDate;

    @Schema(description="是否为站长", name="isStationmaster")
    private boolean isStationMaster;

}
