package com.example.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.web.HomeController;
import com.example.demo.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {
	
	static final Log log = LogFactory.getLog(UserControllerTest.class);
	
	private MockMvc mvc;
	
	/**
	 * 设置需要测试都Controller
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception{
		mvc = MockMvcBuilders.standaloneSetup(
				new HomeController(), 
				new UserController()).build();
	}
	
	//@Test
	public void testHomeController() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(equalTo(HomeController.HOME_RESULT)));
	}
	
	@Test
	public void testUserController() throws Exception{
		RequestBuilder request = null;
		MvcResult result = null;
		String content = null;
		
		// 1、get查一下user列表，应该为空
		request = get("/users/");
		mvc.perform(request)
			//.andExpect(status().isOk())
			.andExpect(content().string(equalTo("[]")));
		
		// 2、post提交一个user
		request = post("/users/")
				.param("id", "1")
				.param("name", "测试大师")
				.param("age", "20");
		mvc.perform(request)
			//.andDo(MockMvcResultHandlers.print())
			//.andExpect(status().isOk())
			.andExpect(content().string(equalTo(UserController.SUCCESS)));
		
		// 3、get获取user列表，应该有刚才插入的数据
		request = get("/users/");
		result = mvc.perform(request)
			//.andExpect(status().isOk())
			.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")))
			.andReturn();
		content = result.getResponse().getContentAsString();
		//log.warn(content);
		
		// 4、put修改id为1的user
		request = put("/users/1")
				.param("name", "测试终极大师")
				.param("age", "30");
		mvc.perform(request)
			//.andExpect(status().isOk())
			.andExpect(content().string(equalTo(UserController.SUCCESS)));
		
		// 5、get一个id为1的user
		request = get("/users/1");
		result = mvc.perform(request)
			//.andDo(MockMvcResultHandlers.print())
			//.andExpect(status().isOk())
			.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")))
			.andReturn();
		content = result.getResponse().getContentAsString();
		log.warn(content);
		
		// 6、del删除id为1的user
		request = delete("/users/1");
		mvc.perform(request)
			.andExpect(content().string(equalTo(UserController.SUCCESS)));

		// 7、get查一下user列表，应该为空
		request = get("/users/");
		mvc.perform(request)
			//.andExpect(status().isOk())
			.andExpect(content().string(equalTo("[]")));
	}
}
