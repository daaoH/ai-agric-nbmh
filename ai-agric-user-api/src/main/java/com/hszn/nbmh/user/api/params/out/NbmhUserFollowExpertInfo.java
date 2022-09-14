package com.hszn.nbmh.user.api.params.out;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 关注的兽医专家记录详情响应实体
 * </p>
 *
 * @author MCR
 * @since 2022-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NbmhUserFollowExpertInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 养殖户id
     */
    @Schema(name = "userId", description = "养殖户id")
    private Long userId;

    /**
     * 养殖户真实姓名
     */
    @Schema(name = "userName", description = "养殖户真实姓名")
    private String userName;

    /**
     * 专家用户id
     */
    @Schema(name = "expertId", description = "专家用户id")
    private Long expertId;

    /**
     * 专家真实姓名
     */
    @Schema(name = "expertName", description = "专家真实姓名")
    private String expertName;

    /**
     * 专家头像
     */
    @Schema(name = "expertAvatar", description = "专家头像")
    private String expertAvatar;

    /**
     * 工作年限
     */
    @Schema(name = "workYear", description = "工作年限")
    private String workYear;

    /**
     * 累计接诊次数
     */
    @Schema(name = "admissions", description = "累计接诊次数")
    private Integer admissions;

    /**
     * 兽医类型0:非官方，1:官方
     */
    @Schema(name = "doctorType", description = "兽医类型0:非官方，1:官方")
    private Integer doctorType;

    /**
     * 擅长畜种0:猪，1:牛，2：羊；3：鸡；4：鸭；5：鹅；6：狗，以逗号分割
     */
    @TableField(condition = SqlCondition.LIKE)
    @Schema(name = "goodAnimalType", description = "擅长畜种0:猪，1:牛，2：羊；3：鸡；4：鸭；5：鹅；6：狗")
    private String goodAnimalType;

}
