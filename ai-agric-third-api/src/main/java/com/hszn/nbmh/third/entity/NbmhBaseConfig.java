package com.hszn.nbmh.third.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 全局配置表
 * </p>
 *
 * @author wangjun
 * @since 2022-08-26
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
@Schema(name="全局配置对象")
public class NbmhBaseConfig implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value="id", type=IdType.AUTO)
    @Schema(name="id", description="id")
    private Long id;

    /**
     * 业务类型
     */
    @Schema(name="subject", description="业务类型")
    private String subject;

    /**
     * key
     */
    @Schema(name="configKey", description="key")
    private String configKey;

    /**
     * value
     */
    @Schema(name="configValue", description="value")
    private String configValue;

    /**
     * 创建时间
     */
    @Schema(name="createTime", description="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(name="updateTime", description="更新时间")
    private Date updateTime;

    /**
     * 状态
     */
    @Schema(name="status", description="状态")
    private Integer status;


}
