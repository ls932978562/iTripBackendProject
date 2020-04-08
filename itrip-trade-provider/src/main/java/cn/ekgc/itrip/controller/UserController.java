package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Api(value = "用户信息交互接口",tags = "用户信息交互接口")
@RestController("userController")
@RequestMapping("/user")
public class UserController {

	@ApiOperation(value = "查询用户信息列表",produces = "application/json",httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "User",value = "查询对象")
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "响应成功")
	})
	@PostMapping("/queryUserList")
	public List<User> queryUserList(@RequestBody User query) throws Exception {
		return new ArrayList<User>();
	}

	@PostMapping(value = "/save")
	public boolean saveUser(@RequestBody User user) throws Exception {
		return true;
	}

	@PostMapping(value = "/update")
	public boolean updateUser(@RequestBody User user) throws Exception {
		return true;
	}

	@GetMapping(value = "/{id}")
	public User queryById(@PathVariable("id") Long id) throws Exception {
		return new User();
	}
}
