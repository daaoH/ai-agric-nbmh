package com.hszn.nbmh.common.security.annotation;

import com.hszn.nbmh.common.security.component.CustomResourceServerConfiguration;
import com.hszn.nbmh.common.security.component.ResourceServerAutoConfiguration;
import com.hszn.nbmh.common.security.feign.FeignClientConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.lang.annotation.*;

/**
 * @Author：袁德民
 * @Description:　资源服务
 * @Date:上午9:13 22/8/17
 * @Modified By:
 */

@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ ResourceServerAutoConfiguration.class, CustomResourceServerConfiguration.class,
        FeignClientConfiguration.class })
public @interface EnableResourceServer {
}
