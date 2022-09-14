package com.hszn.nbmh.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 农牧币记录信息
 * </p>
 *
 * @author wangjun
 * @since 2022-09-14
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class NbmhUserCoinRecord implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value="id", type=IdType.AUTO)
    private Long id;

    /**
     * 业务id
     */
    private Long bizId;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名字
     */
    private String userName;

    /**
     * 是否为收入(0:false,1:true)
     */
    private Integer isIncome;

    /**
     * 状态0:正常,1:冻结
     */
    private Integer status;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;


}
