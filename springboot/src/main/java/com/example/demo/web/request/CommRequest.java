package com.example.demo.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="请求模型")
public class CommRequest<D> {
	
	/**
	 * virtual request data
	 */
	@ApiModelProperty(name="data")
	private D data;

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}
	
}
