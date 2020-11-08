package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sangshanchun
 * @brief
 * @date 2020/10/26 19:44
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	/**
	 * 解决跨域问题
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");
	}
}