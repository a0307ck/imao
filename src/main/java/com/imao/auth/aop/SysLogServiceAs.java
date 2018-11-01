package com.imao.auth.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
* <p>Title: 日志注解</p>
* <p>Description:拦截 Service,用于logr</p>
* <p>Company: ds </p> 
* @date 2018年7月19日 上午10:48:59
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogServiceAs {
	
	String description() default "";

	String module() default "";
}
