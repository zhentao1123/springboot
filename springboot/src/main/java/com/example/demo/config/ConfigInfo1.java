package com.example.demo.config;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
public class ConfigInfo1 {

	private String profile;
	
	private String serverPort;
	
	private Integer testInt;
	
	private String testString;
}
