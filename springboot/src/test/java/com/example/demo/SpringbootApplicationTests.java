package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.config.ConfigInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	private static final Log log = LogFactory.getLog(SpringbootApplicationTests.class);
	
	@Autowired
	ConfigInfo configInfo;
	
	@Test
	public void contextLoads() {
		log.debug(configInfo.toString());
		System.out.println(configInfo.toString());
	}

}
