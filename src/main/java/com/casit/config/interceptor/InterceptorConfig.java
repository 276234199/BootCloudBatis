package com.casit.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//弃用extends WebMvcConfigurerAdapter，直接实现WebMvcConfigurer接口
public class InterceptorConfig  implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebInterceptor interceptor = new WebInterceptor();
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/login.do","/static/**");
        System.out.println("===========   拦截器注册完毕   ===========");
    }


	
	
	
	
}
