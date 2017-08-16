package com.example.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="用户模型")
public class User {
	@ApiModelProperty(name="ID")
	private Long id;
	
	@ApiModelProperty(name="姓名")
    private String name;
	
	@ApiModelProperty(name="年龄")
    private Integer age;
}
