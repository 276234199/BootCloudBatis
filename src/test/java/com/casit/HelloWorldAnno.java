package com.casit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Retention(RetentionPolicy.RUNTIME)是定义注解所必须的。 @Retention是注解的注解.称为注解的元注解
@Retention(RetentionPolicy.RUNTIME)
//@Target 注解位置：    方法上                     属性上                        类上
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.TYPE})
public @interface HelloWorldAnno {
	
	public String name() default "大虎逼"; 
	public int age() default 23;

}
