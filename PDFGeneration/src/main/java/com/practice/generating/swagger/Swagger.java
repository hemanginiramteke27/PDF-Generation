package com.practice.generating.swagger;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@Configuration
@EnableSwagger2
public class Swagger implements WebMvcConfigurer {
	private final String serviceTitle = "EMPLOYEE-MANAGEMENT-SERVICE";
	private final String serviceDescription = "This document contains APIs of Employee-service";
	private final String version = "1.0.0";

	@Bean
	public Docket configApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.ugroup.velo.controller")).build()
				.apiInfo(apiEndPointInfo());
	}

	private ApiInfo apiEndPointInfo() {
		return new ApiInfoBuilder().title(serviceTitle).description(serviceDescription).version(version).build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
