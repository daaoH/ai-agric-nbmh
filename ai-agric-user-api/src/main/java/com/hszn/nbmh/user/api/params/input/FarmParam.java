package com.hszn.nbmh.user.api.params.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @Author：袁德民
 * @Description: 基础注册参数
 * @Date:下午9:25 22/8/23
 * @Modified By:
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@Accessors(chain=true)
@Schema(description="基础注册参数对象")
public class FarmParam {

    /**
     * 防疫站id
     */
    @Schema(name="preventStationId", description="防疫站id")
    private Long preventStationId;

    /**
     * 养殖户id
     */
    @Schema(name="farmerId", description="养殖户id")
    private Long farmerId;

    /**
     * 养殖户场名称
     */
    @Schema(name="farmName", description="养殖户场名称")
    private String farmName;

    /**
     * 经营年限
     */
    @Schema(name="manageYear", description="经营年限")
    private String manageYear;


    /**
     * 团队人数
     */
    @Schema(name="teamNum", description="团队人数")
    private String teamNum;

    /**
     * 经营地址
     */
    @Schema(name="farmAddress", description="经营地址")
    private String farmAddress;

    /**
     * 信息录入人（防疫员）id
     */
    @NotNull(message="新增数据时信息录入人（防疫员）userId不能为空")
    @Schema(name="userId", description="信息录入人（防疫员）id")
    private Long userId;

    /**
     * 防疫员姓名
     */
    @NotBlank(message="新增数据时防疫员姓名userName不能为空")
    @Schema(name="userName", description="防疫员姓名")
    private String userName;


    /**
     * 养殖规模
     */
    @Schema(name="farmScale", description="养殖规模")
    private String farmScale;

    /**
     * 养殖动物json数据
     */
    @NotBlank(message="新增数据时养殖动物数量farmAnimalJson不能为空")
    @Schema(name="farmAnimalJson", description="养殖动物json数据")
    private String farmAnimalJson;


}
