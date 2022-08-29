package com.hszn.nbmh.common.core.annotation;

import com.hszn.nbmh.common.core.enums.DataMaskingEnum;

import java.lang.annotation.*;

/**
 * @Author：袁德民
 * @Description: 数据脱敏注解
 * @Date:下午8:10 22/8/26
 * @Modified By:
 */

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataMasking {

    DataMaskingEnum maskEnum() default DataMaskingEnum.NO_MASK;

}
