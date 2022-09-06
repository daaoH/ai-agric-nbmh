package com.hszn.nbmh.admin.api.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @Author：袁德民
 * @Description: 菜单类型
 * @Date:下午9:56 22/9/6
 * @Modified By:
 */

@Getter
@RequiredArgsConstructor
public enum MenuTypeEnum {

    /**
     * 左侧菜单
     */
    LEFT_MENU("0", "left"),

    /**
     * 顶部菜单
     */
    TOP_MENU("2", "top"),

    /**
     * 按钮
     */
    BUTTON("1", "button");

    /**
     * 类型
     */
    private final String type;

    /**
     * 描述
     */
    private final String description;
}
