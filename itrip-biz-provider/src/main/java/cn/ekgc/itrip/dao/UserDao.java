package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.User;

import java.util.List;
import java.util.Map;

/**
 *<b>爱旅行-主业务模块生产者业务层接口实现类</b>
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
	int updateUserActivateState(User user);
}
