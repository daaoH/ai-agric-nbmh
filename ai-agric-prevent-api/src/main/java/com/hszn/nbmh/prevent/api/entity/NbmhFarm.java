package com.hszn.nbmh.prevent.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 养殖场信息表
 * </p>
 *
 * @author MCR
 * @since 2022-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("nbmh_farm")
@ApiModel(value = "NbmhFarm对象", description = "养殖场信息")
public class NbmhFarm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(message = "新增数据时Id必须为null", groups = {Save.class})
    @NotNull(message = "更新或删除数据时Id不能为空", groups = {Update.class, Delete.class})
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 防疫站id
     */
    @Schema(name = "preventStationId", description = "防疫站id")
    private Long preventStationId;

    /**
     * 养殖户id
     */
    @Schema(name = "farmerId", description = "养殖户id")
    private Long farmerId;

    /**
     * 养殖户场名称
     */
    @Schema(name = "farmName", description = "养殖户场名称")
    private String farmName;

    /**
     * 经营年限
     */
    @Schema(name = "manageYear", description = "经营年限")
    private String manageYear;

    /**
     * 经营范围
     */
    @Schema(name = "manageScope", description = "经营范围")
    private String manageScope;

    /**
     * 团队人数
     */
    @Schema(name = "teamNum", description = "团队人数")
    private String teamNum;

    /**
     * 经营地址
     */
    @Schema(name = "farmAddress", description = "经营地址")
    private String farmAddress;

    /**
     * 信息录入人（防疫员）id
     */
    @NotNull(message = "新增数据时信息录入人（防疫员）userId不能为空", groups = {Save.class})
    @Schema(name = "userId", description = "信息录入人（防疫员）id")
    private Long userId;

    /**
     * 防疫员姓名
     */
    @NotBlank(message = "新增数据时防疫员姓名userName不能为空", groups = {Save.class})
    @Schema(name = "userName", description = "防疫员姓名")
    private String userName;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;

    /**
     * 状态 0正常 -1停业
     */
    @Schema(name = "status", description = "状态 0正常 -1停业")
    private Integer status;

    /**
     * 养殖规模
     */
    @Schema(name = "farmScale", description = "养殖规模")
    private String farmScale;

    /**
     * 养殖动物json数据
     */
    @NotBlank(message = "新增数据时养殖动物数量farmAnimalJson不能为空", groups = {Save.class})
    @Schema(name = "farmAnimalJson", description = "养殖动物json数据")
    private String farmAnimalJson;

    /**
     * 经度(方便计算距离)
     */
    @Schema(name = "longitude", description = "经度(方便计算距离)")
    private String longitude;

    /**
     * 纬度(方便计算距离)
     */
    @Schema(name = "latitude", description = "纬度(方便计算距离)")
    private String latitude;

    public interface Save extends Default {
    }

    public interface Update extends Default {
    }

    public interface Delete extends Default {
    }


}
