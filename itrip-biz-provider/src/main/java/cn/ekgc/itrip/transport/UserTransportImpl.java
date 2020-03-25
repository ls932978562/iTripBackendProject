package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.ItripAddUserLinkUserVO;
import cn.ekgc.itrip.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
/**
 * <b>爱旅行-用户模块传输层接口s实现类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("userTransport")
@RequestMapping("/user/trans")
public class UserTransportImpl implements UserTransport {

	@Resource(name = "userService")
	private UserService userService;

	/**
	 * <b>查询用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/ckusr")
	public List<User> getUserByQuery(@RequestBody User user) throws Exception {
		//直接进行调用
		return userService.getUserByQuery(user);
	}

	/**
	 * <b>注册用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/doregister")
	public boolean addUserForRegistry(@RequestBody User user) throws Exception {
		//直接进行调用
		return userService.addUserForRegistry(user);
	}

	/**
	 * <b>用户账户激活</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/activate")
	public boolean userActivate(@RequestBody User user) throws Exception {
		return userService.userActivate(user);
	}

	/**
	 * <b>通过用户的userCode在redis中获得用户激活码</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/activeCode")
	public String getUserActiveCode(@RequestParam String userCode) throws Exception {
		return userService.getUserActiveCode(userCode);
	}

	/**
	 * <b>查询常用联系人</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/linkUser")
	public List<ItripUserLinkUser> getLinkUser(@RequestBody ItripUserLinkUser linkUser) throws Exception {
		return userService.getLinkUser(linkUser);
	}

	/**
	 * <b>添加常用联系人</b>
	 * @param itripUserLinkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addLinkUser")
	public int adduserlinkuser(@RequestBody ItripUserLinkUser itripUserLinkUser) throws Exception {
		return userService.adduserlinkuser(itripUserLinkUser);
	}

	/**
	 * <b>修改常用联系人信息</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/updateLinkUser")
	public int updateUserLinkUser(@RequestBody ItripAddUserLinkUserVO addUserLinkUserVO) throws Exception {
		return userService.updateUserLinkUser(addUserLinkUserVO);
	}

	/**
	 * <b>删除常用联系人</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/deluserlinkuser")
	public int delUserlinkUser(@RequestParam Integer linkUserId) throws Exception {
		return userService.delUserlinkUser(linkUserId);
	}
}
