
package com.hszn.nbmh.common.annotation;

import com.hszn.nbmh.common.config.SwaggerAutoConfiguration;
import com.hszn.nbmh.common.support.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 swagger
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableConfigurationProperties(SwaggerProperties.class)
@Import({ SwaggerAutoConfiguration.class })
public @interface EnableSwagger {

}
