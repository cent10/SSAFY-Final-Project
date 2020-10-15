package com.activityx.allei.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
		basePackages = "com.activityx.allei.dao"
)
public class DatabaseConfig {
	
}