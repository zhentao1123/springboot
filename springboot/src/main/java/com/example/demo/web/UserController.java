package com.example.demo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;

/**
 * REST Demo
 * @author zhangzhentao
 *
 */
@RestController
@RequestMapping("/users")
@Api(hidden=false)
public class UserController {
	
	public static final String SUCCESS = "success";
	
	static final Log log = LogFactory.getLog(UserController.class);
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

	/**
	 * 处理"/users/"的GET请求，用来获取用户列表
	 * 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
	 * @return
	 */
	@ApiOperation(value="获取用户列表", notes="")
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<User> getUserList(){
		List<User> l = new ArrayList<User>(users.values());
		return l;
	}
	
	/**
	 * 处理"/users/"的POST请求，用来创建User
	 * 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
	 * @param user
	 * @return
	 */
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String postUser(@RequestBody User user){
		users.put(user.getId(), user);
		log.warn(users.toString());
		return SUCCESS;
	}
	
	/**
	 * 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
	 * url中的id可通过@PathVariable绑定到函数的参数中
	 * @param id
	 * @return
	 */
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable Long id){
		return users.get(id);
	}
	
	/**
	 * 处理"/users/{id}"的PUT请求，用来更新User信息
	 * @param id
	 * @param user
	 * @return
	 */
	@ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public String putUser(@PathVariable Long id, @RequestBody User user){
		User u = users.get(id);
		u.setAge(user.getAge());
		u.setName(user.getName());
		users.put(id, u);
		return SUCCESS;
	}
	
	/**
	 * 处理"/users/{id}"的DELETE请求，用来删除User
	 * @param id
	 * @return
	 */
	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		users.remove(id);
		return SUCCESS;
	}
}
