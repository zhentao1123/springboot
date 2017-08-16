package com.example.demo.config;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ConfigInfo {

	@Value("${profile}")
	private String profile;
	
	@Value("${server.port}")
	private String serverPort;
	
	@Value("${mytest.test2}")
	private Integer testInt;
	
	@Value("${mytest.value}")
	private String testString;
}
