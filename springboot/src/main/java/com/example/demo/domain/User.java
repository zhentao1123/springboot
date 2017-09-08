package com.example.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

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
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    
	public User(Long id, String name, Integer age, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	public User() {
		super();
	}
}
