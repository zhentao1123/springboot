package com.example.demo.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//@ApiModel(description="响应模型")
public class CommResponse<D> {
	
	public static final int CODE_SUCCEED = 1;
	public static final int CODE_FAIL = 0;
	public static final String COMM_MESSAGE_SUCCEED = "succeed";
	public static final String COMM_MESSAGE_FAIL = "fail";
	
	//@ApiModelProperty(name="code")
	private Integer code;
	//@ApiModelProperty(name="message")
	private String message;
	
	/**
	 * virtual response data
	 */
	//@ApiModelProperty(name="data")
	private D data;
	
	public CommResponse(Integer code, String message, D data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public static <D> CommResponse<D> getInstances4Succeed(D data){
		CommResponse<D> instances = new CommResponse<D>(CODE_SUCCEED, COMM_MESSAGE_SUCCEED, data);
		return instances;
	}

	public static <D> CommResponse<D> getInstances4Fail(D data){
		CommResponse<D> instances = new CommResponse<D>(CODE_FAIL, COMM_MESSAGE_FAIL, null);
		return instances;
	}

	
}