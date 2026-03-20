package com.grauzev.pcremote.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.grauzev.pcremote.api.ApiTokenInterceptor;

/**
 * Spring MVC configuration.
 * Used to register interceptors and other web-related settings.
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	// === Variables ===
	private final ApiTokenInterceptor apiTokenInterceptor;
	
	// === Constructor ===
	public WebConfig(ApiTokenInterceptor apiTokenInterceptor) {
		this.apiTokenInterceptor = apiTokenInterceptor;
	}
	
	// === Public API ===
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiTokenInterceptor).addPathPatterns("/api/commands/**");
	}

}
