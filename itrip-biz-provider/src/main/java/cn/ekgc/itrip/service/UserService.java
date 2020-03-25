package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.ItripAddUserLinkUserVO;
import cn.ekgc.itrip.pojo.vo.UserVo;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

/**
 *<b>爱旅行-主业务模块生产者业务层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UserService {
	/**
	 * <b>查询用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> getUserByQuery(User user)throws Exception;

	/**
	 * <b>注册用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean addUserForRegistry(User user)throws Exception;

	/**
	 * <b>用户账户激活</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	boolean userActivate(User user) throws Exception;

	/**
	 * <b>获得用户激活码</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	String getUserActiveCode(String userCode)throws Exception;

	/**
	 * <b>查询常用联系人</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	List<ItripUserLinkUser> getLinkUser(ItripUserLinkUser linkUser)throws Exception;


	/**
	 * <b>添加常用联系人</b>
	 * @param itripUserLinkUser
	 * @return
	 * @throws Exception
	 */
	int adduserlinkuser(ItripUserLinkUser itripUserLinkUser)throws Exception;

	/**
	 * <b>修改常用联系人信息</b>
	 * @return
	 * @throws Exception
	 */
	int updateUserLinkUser(ItripAddUserLinkUserVO addUserLinkUserVO)throws Exception;


	/**
	 * <b>删除常用联系人</b>
	 * @return
	 * @throws Exception
	 */
	int delUserlinkUser(Integer linkUserId)throws Exception;
}
