package com.hszn.nbmh.common.core.annotation;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.hszn.nbmh.common.core.mould.DataMaskingSerializer;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author：袁德民
 * @Description:
 * @Date:下午8:19 22/8/26
 * @Modified By:
 */

@Slf4j
public class DataMaskingAnnotationIntrospector extends NopAnnotationIntrospector {

    @Override
    public Object findSerializer(Annotated am) {
        DataMasking annotation = am.getAnnotation(DataMasking.class);
        if (annotation != null) {
            return new DataMaskingSerializer(annotation.maskEnum().operation());
        }
        return null;
    }
}
