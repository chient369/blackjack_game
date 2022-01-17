package com.blackjack.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("/static/js/**")
				.addResourceLocations("classpath:/META-INF/resources/static/js/");
		registry.addResourceHandler("/static/image/**")
				.addResourceLocations("classpath:/META-INF/resources/static/image/");
		registry.addResourceHandler("/static/css/**")
				.addResourceLocations("classpath:/META-INF/resources/static/css/");
	}


}
