package com.example.demo.domain;

import java.util.Date;

import lombok.Data;

@Data
//@ApiModel(description="用户模型")
public class User {
	//@ApiModelProperty(name="ID")
	private Long id;
	
	//@ApiModelProperty(name="姓名")
    private String name;
	
	//@ApiModelProperty(name="年龄")
    private Integer age;
    
    //@ApiModelProperty(name="生日")
    private Date birthday;
}
