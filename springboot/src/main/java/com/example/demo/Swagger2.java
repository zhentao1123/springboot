package com.example.demo;

import org.springframework.context.annotation.Configuration;

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
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage(basePackage))
			.paths(PathSelectors.any())
			.build();
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
