package com.hszn.nbmh.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 审核通过
     */
    PASS(0, "审核通过"),
    /**
     * 审核拒绝
     */
    REFUSE(1, "审核拒绝"),
    /**
     * 审核中
     */
    AUDIT(2, "审核中"),

    /**
     * 撤销
     */
    ANNUL(3, "撤销审核");

    /**
     * 类型
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;
}
