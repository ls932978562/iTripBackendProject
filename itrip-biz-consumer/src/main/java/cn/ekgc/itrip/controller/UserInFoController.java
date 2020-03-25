package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.ItripAddUserLinkUserVO;
import cn.ekgc.itrip.pojo.vo.ItripSearchUserLinkUserVO;
import cn.ekgc.itrip.pojo.vo.ModifyUserLinkUserVO;
import cn.ekgc.itrip.transport.UserTransport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.internal.schedulers.NewThreadWorker;

import javax.servlet.http.Cookie;
import javax.xml.ws.soap.Addressing;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <b>爱旅行-常用联系人控制器</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("userInFoController")
@RequestMapping("/biz/api/userinfo")
public class UserInFoController extends BaseController {

	@Autowired
	private UserTransport userTransport;
	/**
	 * <b>查询常用联系人</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryuserlinkuser")
	public ResponseDto<Object> queryuserlinkuser(@RequestBody ItripUserLinkUser linkUser)throws Exception{
		List<ItripUserLinkUser> linkUserList = new LinkedList<ItripUserLinkUser>();
		//获得Cookie对象
		Cookie cookies[] = request.getCookies();
		String  userCode = "";
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")){
				userCode = cookie.getValue();
			}
		}
		User user = new User();
		user.setUserCode(userCode);
		List<User> userList = userTransport.getUserByQuery(user);
		//根据用户名只能查到一个用户
		if(userList != null && userList.size() > 0){
			//封装常用联系人查询列表
			linkUser.setUserId(userList.get(0).getId());
			//进行常用联系人查询
			linkUserList = userTransport.getLinkUser(linkUser);
		}
		return ResponseDto.success(linkUserList);
	}


	/**
	 * <b>添加常用联系人</b>
	 * @param addUserLinkUserVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/adduserlinkuser")
	public ResponseDto<Object> addUserLinkUser(@RequestBody ItripAddUserLinkUserVO addUserLinkUserVO)throws Exception{
		//获得Cookie对象
		Cookie cookies[] = request.getCookies();
		String  userCode = "";
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")){
				userCode = cookie.getValue();
			}
		}
		User user = new User();
		user.setUserCode(userCode);
		List<User> userList = userTransport.getUserByQuery(user);
		Long userId = userList.get(0).getId();

		//封装参数
		ItripUserLinkUser itripUserLinkUser = new ItripUserLinkUser();
		itripUserLinkUser.setLinkUserName(addUserLinkUserVO.getLinkUserName());
		itripUserLinkUser.setLinkIdCard(addUserLinkUserVO.getLinkIdCard());
		itripUserLinkUser.setLinkPhone(addUserLinkUserVO.getLinkPhone());
		itripUserLinkUser.setUserId(userId);
		itripUserLinkUser.setCreationDate(new Date());
		itripUserLinkUser.setModifyDate(new Date());
		itripUserLinkUser.setLinkIdCardType(addUserLinkUserVO.getLinkIdCardType());

		//进行增加联系人
		int count = userTransport.adduserlinkuser(itripUserLinkUser);
		if(count > 0){
			return ResponseDto.success("新增联系人成功");
		}
		return ResponseDto.failure("新增联系人失败！");
	}


	/**
	 * <b>修改常用联系人信息</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/modifyuserlinkuser")
	public ResponseDto<Object> modifyuserlinkuser(@RequestBody ModifyUserLinkUserVO modifyUserLinkUserVO)throws Exception{
		//封装常用联系人信息，复制对象
		ItripAddUserLinkUserVO addUserLinkUserVO = new ItripAddUserLinkUserVO();
		BeanUtils.copyProperties(modifyUserLinkUserVO, addUserLinkUserVO);
		//由于前段没有传递userId，暂时将userId作为Id使用
		addUserLinkUserVO.setUserId(modifyUserLinkUserVO.getId());
		//进行修改
		int count = userTransport.updateUserLinkUser(addUserLinkUserVO);
		if(count > 0){
			return ResponseDto.success("修改联系人成功！");
		}
		return ResponseDto.failure("修改联系人失败！");
	}

	/**
	 * <b>删除常用联系人</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/deluserlinkuser")
	public ResponseDto<Object> delUserlinkUser(@RequestParam Integer ids)throws Exception{
		//进行删除
		int count = userTransport.delUserlinkUser(ids);
		if(count > 0){
			return ResponseDto.success("删除联系人成功！");
		}
		return ResponseDto.failure("删除联系人失败！");
	}
}
