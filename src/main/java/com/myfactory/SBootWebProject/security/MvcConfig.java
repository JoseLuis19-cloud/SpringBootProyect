package com.myfactory.SBootWebProject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		// We observe the following points here.
		// 1. When we access URL /home2 then paginainicio.html  will run.
		// 3. When we access URL /detail then we will get header response with 400 HTTP status code.
	//	registry.addViewController("/home").setViewName("caca");
	//	registry.addViewController("/home2").setViewName("paginainicio");
		registry.addViewController("/403").setViewName("403");
	}

}
