package com.example.demo.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//@ApiModel(description="请求模型")
public class CommRequest<D> {
	//can add some client info here
	
	/**
	 * virtual request data
	 */
	//@ApiModelProperty(name="data")
	private D data;
}