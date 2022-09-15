package com.hszn.nbmh.cms.api.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 字典项
 * </p>
 *
 * @author 李肖
 * @since 2022-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_Dict_item")
@ApiModel(value = "SysDict对象", description = "字典信息")
public class SysDictItem implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(name = "id", description = "主键id")
    private Long id;

    /**
     * 字典ID
     */
    @Schema(name = "dictId", description = "字典id")
    private Long dictId;

    /**
     * 值
     */
    @Schema(name = "value", description = "值")
    private String value;

    /**
     * 标签
     */
    @Schema(name = "label", description = "标签")
    private String label;

    /**
     * 字典类型
     */
    @Schema(name = "type", description = "字典类型")
    private String type;

    /**
     * 描述
     */
    @Schema(name = "description", description = "描述")
    private String description;

    /**
     * 排序（升序）
     */
    @Schema(name = "sortOrder", description = "排序（升序）")
    private Integer sortOrder;

    /**
     * 备注
     */
    @Schema(name = "remark", description = "备注")
    private String remark;

    /**
     * 删除标记
     */
    @Schema(name = "status", description = "删除标记")
    private String status;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @Schema(name = "createBy", description = "创建人")
    private String createBy;

    /**
     * 修改人
     */
    @Schema(name = "updateBy", description = "修改人")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(name = "updateTime", description = "更新时间")
    private Date updateTime;


}
