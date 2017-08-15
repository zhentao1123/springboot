package com.example.demo.to;

import java.io.Serializable;

import lombok.Data;

@Data
public class ConfigInfoTO implements Serializable {

	private static final long serialVersionUID = 2891835308783294547L;
	
	private String profile;
	
	private String serverPort;
}
