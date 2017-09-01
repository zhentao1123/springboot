package com.example.demo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.exception.MyException;
import com.example.demo.service.UserService;
import com.example.demo.web.request.CommRequest;
import com.example.demo.web.response.CommResponse;

/**
 * do all process with get method
 * @author zhangzhentao
 *
 */
@RestController
@RequestMapping("/v3/users")
@Api(hidden=false, tags="User接口")
public class UserControllerV3 {
	
	static final Log log = LogFactory.getLog(UserControllerV2.class);
	static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

	@Autowired
	UserService userService;
	
	/**
	 * 
	 * @return
	 * @throws MyException
	 */
	@ApiOperation(value="获取用户列表", notes="")
	@ApiImplicitParam(name = "request", value = "请求对象", required = true, dataType = "CommRequest<Void>")
	@RequestMapping(value="/getUserList", method=RequestMethod.POST)
	public CommResponse<List<User>> getUserList() throws MyException{
		//List<User> l = new ArrayList<User>(users.values());
		List<User> l = userService.getUserList();
		return CommResponse.getInstances4Succeed(l);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws MyException
	 */
	@ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "request", value = "user对象", required = true, dataType = "CommRequest<User>")
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public CommResponse<Void> addUser(@RequestBody CommRequest<User> request) throws MyException{
		User user = request.getData();
		//users.put(user.getId(), user);
		userService.addUser(user);
		log.warn(users.toString());
		return CommResponse.getInstances4Succeed(null);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws MyException
	 */
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "request", value = "用户ID", required = true, dataType = "CommRequest<Long>")
	@RequestMapping(value="/getUser", method=RequestMethod.POST)
	public CommResponse<User> getUser(@RequestBody CommRequest<Long> request) throws MyException{
		Long id = request.getData();
		User user = null;
		//user = users.get(id);
		user = userService.getUser(id);
		return CommResponse.getInstances4Succeed(user);
	}
	
	/**
	 * 
	 * @param id
	 * @param user
	 * @return
	 * @throws MyException
	 */
	@ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParam(name = "request", value = "user对象", required = true, dataType = "CommRequest<User>")
	@RequestMapping(value="/editUser", method=RequestMethod.POST)
	public CommResponse<Void> editUser(@RequestBody CommRequest<User> request) throws MyException{
		User user = request.getData();
		Long id = user.getId();
		//User u = users.get(id);
		//u.setAge(user.getAge());
		//u.setName(user.getName());
		//users.put(id, u);
		User u = userService.getUser(id);
		u.setAge(user.getAge());
		u.setName(user.getName());
		u.setBirthday(user.getBirthday());
		userService.editUser(id, user);
		return CommResponse.getInstances4Succeed(null);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws MyException
	 */
	@ApiOperation(value="删除用户", notes="根据url的id来指定删除对象")
	@ApiImplicitParam(name = "request", value = "用户ID", required = true, dataType = "CommRequest<Long>")
	@RequestMapping(value="/deleteUser", method=RequestMethod.POST)
	public CommResponse<Void> deleteUser(@RequestBody CommRequest<Long> request) throws MyException {
		Long id = request.getData();
		//users.remove(id);
		userService.removeUser(id);
		return CommResponse.getInstances4Succeed(null);
	}
}
