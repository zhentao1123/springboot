package com.example.demo.exception;

import lombok.Data;

/**
 * @author zhangzhentao
 *
 */
@SuppressWarnings("serial")
@Data
public class MyException extends Exception {

	private String info;
	
    public MyException(String message) {
        super(message);
    }

}
