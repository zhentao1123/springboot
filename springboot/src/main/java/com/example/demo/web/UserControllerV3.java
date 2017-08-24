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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.exception.MyException;
import com.example.demo.service.UserService;

/**
 * REST Demo
 * @author zhangzhentao
 *
 */
@RestController
@RequestMapping("/v3/users")
@Api(hidden=false, tags="User接口")
public class UserControllerV3 {
	
	static final Log log = LogFactory.getLog(UserControllerV2.class);
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
	
	public static final String SUCCESS = "success";

	@Autowired
	UserService userService;
	
	/**
	 * 处理"/users/"的GET请求，用来获取用户列表
	 * 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
	 * @return
	 * @throws MyException 
	 */
	@ApiOperation(value="获取用户列表", notes="")
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<User> getUserList() throws MyException{
		//List<User> l = new ArrayList<User>(users.values());
		List<User> l = userService.getUserList();
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
	public String postUser(@RequestBody User user) throws MyException{
		//users.put(user.getId(), user);
		userService.addUser(user);
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
	public User getUser(@PathVariable Long id) throws MyException{
		//return users.get(id);
		return userService.getUser(id);
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
	public String putUser(@PathVariable Long id, @RequestBody User user) throws MyException{
		//User u = users.get(id);
		//u.setAge(user.getAge());
		//u.setName(user.getName());
		//users.put(id, u);
		User u = userService.getUser(id);
		u.setAge(user.getAge());
		u.setName(user.getName());
		u.setBirthday(user.getBirthday());
		userService.editUser(id, user);
		return SUCCESS;
	}
	
	/**
	 * 处理"/users/{id}"的DELETE请求，用来删除User
	 * @param id
	 * @return
	 * @throws MyException 
	 */
	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) throws MyException {
		//users.remove(id);
		userService.removeUser(id);
		return SUCCESS;
	}
}
