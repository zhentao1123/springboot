package com.example.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	
	static final String basePackage = "com.example.demo.web";
	
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
			.groupName("v1")
			.genericModelSubstitutes(DeferredResult.class)
            .useDefaultResponseMessages(false)
            .forCodeGeneration(true)
            .pathMapping("/")
			.select()
			//.apis(RequestHandlerSelectors.basePackage(basePackage))//匹配包
			//.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))//匹配类注解
			//.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//匹配方法注解
			//.paths(PathSelectors.any())
			//.paths(Predicates.or(PathSelectors.regex("/users/.*")))//过滤的接口
			.build()
			.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot Demo")
                .description("Spring Boot Demo")
                .termsOfServiceUrl("http://www.spring.com/")
                .contact(new Contact("bobz",null,null))
                .version("1.0")
                .build();
    }
}
