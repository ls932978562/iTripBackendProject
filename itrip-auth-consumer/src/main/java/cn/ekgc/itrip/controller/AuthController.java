package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.enums.UserActivatedEnum;
import cn.ekgc.itrip.base.enums.UserRegistryTypeEnum;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.UserVo;
import cn.ekgc.itrip.transport.UserTransport;
import cn.ekgc.itrip.util.CheckFormatUtil;
import cn.ekgc.itrip.util.MD5Util;
import cn.ekgc.itrip.util.JWTUtil;
import cn.ekgc.itrip.util.constant.SystemConstant;
import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiListing;

import java.util.List;

/**
 * <b>爱旅行-认证模块控制器</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Api(value = "用户信息交互接口",tags = "用户信息交互接口")
@RestController("authController")
@RequestMapping("/auth/api")
public class AuthController extends BaseController {

	@Autowired
	private UserTransport userTransport;

	/**
	 * <b>查询用户是否存在进行邮箱注册</b>
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查询用户是否存在",produces = "application/json",httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name",value = "查询用户")
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "响应成功")
	})
	@GetMapping(value = "/ckusr")
	public ResponseDto<Object> checkUserEmailForRegistry(String name) throws Exception{
		//校验用户输入的格式是否正确
		if(name != null && !"".equals(name.trim())){
			//将用户注册的邮箱地址name附给userCode属性
			User user = new User();
			user.setUserCode(name);
			//根据用户信息进行查询用户
			List<User> userList = userTransport.getUserByQuery(user);
			if(userList == null || userList.size() == 0){
				//该用户不存在，可以进行注册
				return ResponseDto.success();
			}else{
				return ResponseDto.failure("该账号已被注册，请登录！");
			}
		}
		return ResponseDto.failure("该账号已被注册，请登录！");
	}

	/**
	 * <b>注册新用户-邮箱</b>
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "注册用户-邮箱",produces = "application/json",httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "UserVo",value = "注册用户")
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "响应成功")
	})
	@PostMapping(value = "/doregister")
	public ResponseDto<Object> registryNewUserByMail(@RequestBody UserVo userVo)throws Exception{
		//进行校验用户输入的格式是否正确
		if(CheckFormatUtil.checkEmailFormat(userVo.getUserCode())
			&& userVo.getUserPassword() != null && !"".equals(userVo.getUserPassword())){
			// 进行唯一性校验
			User query = new User();
			query.setUserCode(userVo.getUserCode());
			List<User> userList = userTransport.getUserByQuery(query);
			if(userList == null || userList.size() == 0){
				//将用户密码进行加密
				userVo.setUserPassword(MD5Util.encrypt(userVo.getUserPassword()));
				//将UserVo的对象转换成User对象,调用spring提供的工具类进行转换
				User user = new User();
				BeanUtils.copyProperties(userVo, user);
				//注册该方法属于自主注册，调用枚举类型
				user.setUserType(UserRegistryTypeEnum.USER_TYPE_REG.getCode());
				//注册时状态设置为未激活，调用枚举类型
				user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
				//拿到用户信息进行注册
				boolean flag = userTransport.addUserForRegistry(user);
				if(flag){
					//成功注册用户
					return ResponseDto.success();
				}
			}
		}
		return ResponseDto.failure("注册失败！");
	}

	/**
	 * <b>用户注册-手机号码</b>
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "注册用户-手机",produces = "application/json",httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "UserVo",value = "注册用户")
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "响应成功")
	})
	@PostMapping("/registerbyphone")
	public ResponseDto<Object> registryNewUserByPhone(@RequestBody UserVo userVo)throws Exception{
		//进行校验用户输入的格式是否正确
		if(CheckFormatUtil.checkPhoneFormat(userVo.getUserCode())
				&& userVo.getUserPassword() != null && !"".equals(userVo.getUserPassword())){
			//进行唯一性校验
			User queryUser = new User();
			queryUser.setUserCode(userVo.getUserCode());
			List<User> userList = userTransport.getUserByQuery(queryUser);
			if(userList == null || userList.size() == 0){
				//将用户密码进行加密
				userVo.setUserPassword(MD5Util.encrypt(userVo.getUserPassword()));
				//将UserVo的对象转换成User对象,调用spring提供的工具类进行转换
				User user = new User();
				BeanUtils.copyProperties(userVo, user);
				//注册该方法属于自主注册，调用枚举类型
				user.setUserType(UserRegistryTypeEnum.USER_TYPE_REG.getCode());
				//注册时状态设置为未激活，调用枚举类型
				user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
				//拿到用户信息进行注册
				boolean flag = userTransport.addUserForRegistry(user);
				if(flag){
					//成功注册用户
					return ResponseDto.success();
				}
			}
			return ResponseDto.failure("该用户已经被注册，请登录！！！");
		}
		return ResponseDto.failure("注册失败！");
	}


	/**
	 * <b>爱旅行-用户激活-邮箱</b>
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "激活用户-邮箱",produces = "application/json",httpMethod = "PUT")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user",value = "用户账号"),
			@ApiImplicitParam(name = "code",value = "用户激活码")
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "响应成功")
	})
	@PutMapping("/activate")
	public ResponseDto<Object> userActivateByMail(String user,String code)throws Exception{
		//检查用户是否输入正确
		if(user != null && !"".equals(user.trim()) && code != null && !"".equals(code)){
			//根据用户输入的信息，进行用户的激活,首先获得用户存储于redis的账户激活码
			String activeCode = userTransport.getUserActiveCode(user);
			//判断用户的激活码是否正确
			if(activeCode.equals(code)){
				//修改用户的激活状态
				User updateUser = new User();
				updateUser.setUserCode(user);
				updateUser.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
				boolean flag = userTransport.userActivate(updateUser);
				return ResponseDto.success();
			}
		}
		return ResponseDto.failure("账号错误或激活码不正确,激活失败！");
	}

	/**
	 * <b>爱旅行-用户激活-手机号码</b>
	 * @param user
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "激活用户-手机",produces = "application/json",httpMethod = "PUT")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "user",value = "用户账号"),
			@ApiImplicitParam(name = "code",value = "用户激活码")
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "响应成功")
	})
	@PutMapping("/validatephone")
	public ResponseDto<Object> userActivateByPhone(String user,String code)throws Exception{
		//检查用户是否输入正确
		if(user != null && !"".equals(user.trim()) && code != null && !"".equals(code)){
			//根据用户输入的信息，进行用户的激活,首先获得用户存储于redis的账户激活码
			String activeCode = userTransport.getUserActiveCode(user);
			//判断用户的激活码是否正确
			if(activeCode.equals(code)){
				//修改用户的激活状态
				User updateUser = new User();
				updateUser.setUserCode(user);
				updateUser.setActivated(UserActivatedEnum.USER_ACTIVATED_YES.getCode());
				boolean flag = userTransport.userActivate(updateUser);
				return ResponseDto.success();
			}
			return ResponseDto.failure("激活码不正确！！！");
		}
		return ResponseDto.failure("账号错误或激活码不正确,激活失败！！！");
	}

	/**
	 * <b>用户登录</b>
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "激活用户-邮箱",produces = "application/json",httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name",value = "用户账号"),
			@ApiImplicitParam(name = "password",value = "用户密码")
	})
	@ApiResponses({
			@ApiResponse(code = 200,message = "响应成功")
	})
	@PostMapping("/dologin")
	public ResponseDto<Object> userLogin(String name,String password)throws Exception{
		if(name != null && !"".equals(name.trim()) && password != null && !"".equals(password.trim())){
			//将用户输入的信息封装成一个User对象
			User query = new User();
			query.setUserCode(name);
			//根据用户信息进行查询用户
			List<User> userList = userTransport.getUserByQuery(query);
			//根据用户的账号和密码只可能查到最多一个对象,并且状态是激活的可用的,并且密码正确
			if(userList != null && (userList.size() > 0)){
				if(userList.get(0).getActivated() == UserActivatedEnum.USER_ACTIVATED_YES.getCode()){
					if((userList.get(0).getUserPassword()).equals(MD5Util.encrypt(password))){
						//使用当前用户id生成token信息
						String token = JWTUtil.createToken(userList.get(0).getId());
						//将token随响应交给浏览器
						response.setHeader("Authorization", token);
						return ResponseDto.success(token);
					}else {
						return ResponseDto.failure("用户密码错误！！！");
					}
				}else {
					return ResponseDto.failure("用户状态未激活");
				}
			}else {
				return ResponseDto.failure("用户名或密码错误，请重新输入！");
			}
		}
			return ResponseDto.failure("请输入用户名和密码");
	}


}






