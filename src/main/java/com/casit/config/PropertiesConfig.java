package com.casit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
* 类说明:引入配置文件
* @author zhouhai
* @version 创建时间：2018年11月22日 下午2:06:55
*/
@Configuration
@PropertySource(value="classpath:/config.properties")
public class PropertiesConfig {

}
