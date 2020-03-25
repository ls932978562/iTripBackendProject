package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.ItripAddUserLinkUserVO;

import java.util.List;
import java.util.Map;

/**
 *<b>爱旅行-主业务模块生产者用户接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UserDao {
	/**
	 * <b>查询用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	List<User> findUserByQuery(User user)throws Exception;

	/**
	 * <b>注册用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	int saveUserForRegistry(User user)throws Exception;

	/**
	 * <b>用户账号激活</b>
	 * @param user
	 * @return
	 */
	int updateUserActivateState(User user)throws Exception;

	/**
	 * <b>查询常用联系人</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	List<ItripUserLinkUser> findUserLinkUser(ItripUserLinkUser linkUser)throws Exception;


	/**
	 * <b>添加常用联系人</b>
	 * @param itripUserLinkUser
	 * @return
	 * @throws Exception
	 */
	int insertUserLinkUser(ItripUserLinkUser itripUserLinkUser)throws Exception;


	/**
	 * <b>修改常用联系人信息</b>
	 * @return
	 * @throws Exception
	 */
	int updateLinkUserInFo(ItripAddUserLinkUserVO addUserLinkUserVO)throws Exception;


	/**
	 * <b>删除常用联系人</b>
	 * @return
	 * @throws Exception
	 */
	int delUserlinkUser(Map<String, Object> map)throws Exception;
}
