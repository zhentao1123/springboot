package com.example.demo.exception;

import lombok.Data;

/**
 * @author zhangzhentao
 *
 */
@SuppressWarnings("serial")
@Data
public class ServiceException extends Exception {

	private String info;
	
    public ServiceException(String message) {
        super(message);
    }

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
