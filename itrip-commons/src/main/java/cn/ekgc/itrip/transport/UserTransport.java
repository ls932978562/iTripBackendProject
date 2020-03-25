package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.ItripAddUserLinkUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>爱旅行-用户信息传输层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/user/trans")
public interface UserTransport {

	/**
	 * <b>查询用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/ckusr")
	List<User> getUserByQuery(@RequestBody User user)throws Exception;

	/**
	 * <b>注册用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/doregister")
	boolean addUserForRegistry(@RequestBody User user)throws Exception;

	/**
	 * <b>用户账户激活</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/activate")
	boolean userActivate(@RequestBody User user)throws Exception;

	/**
	 * <b>获得用户激活码</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/activeCode")
	String getUserActiveCode(@RequestParam String userCode)throws Exception;

	/**
	 * <b>查询常用联系人</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/linkUser")
	List<ItripUserLinkUser> getLinkUser(@RequestBody ItripUserLinkUser linkUser)throws Exception;


	/**
	 * <b>添加常用联系人</b>
	 * @param itripUserLinkUser
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addLinkUser")
	int adduserlinkuser(@RequestBody ItripUserLinkUser itripUserLinkUser)throws Exception;


	/**
	 * <b>修改常用联系人信息</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/updateLinkUser")
	int updateUserLinkUser(@RequestBody ItripAddUserLinkUserVO addUserLinkUserVO)throws Exception;


	/**
	 * <b>删除常用联系人</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/deluserlinkuser")
	int delUserlinkUser(@RequestParam Integer linkUserId)throws Exception;
}
