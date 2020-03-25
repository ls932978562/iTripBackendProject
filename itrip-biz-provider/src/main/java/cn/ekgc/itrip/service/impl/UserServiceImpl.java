package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.UserDao;
import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.ItripAddUserLinkUserVO;
import cn.ekgc.itrip.service.UserService;
import cn.ekgc.itrip.util.CheckFormatUtil;
import cn.ekgc.itrip.util.CreateActivatedCodeUtil;
import cn.ekgc.itrip.util.MailSenderUtil;
import cn.ekgc.itrip.util.SmsSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 *<b>爱旅行-主业务模块生产者业务层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private MailSenderUtil mailSenderUtil;                  //依赖注入发送邮件工具类
	@Autowired
	private StringRedisTemplate redisTemplate;              //依赖注入redis非关系型数据库属性
	@Autowired
	private SmsSenderUtil smsSenderUtil;                    //依赖注入发送信息工具类
	/**
	 * <b>查询用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserByQuery(User user) throws Exception {
		//直接进行调用查询用户
		return userDao.findUserByQuery(user);
	}

	/**
	 * <b>注册用户信息</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean addUserForRegistry(User user) throws Exception {
		//设定用户的创建时间
		user.setCreationDate(new Date());
		user.setModifyDate(new Date());
		int count = userDao.saveUserForRegistry(user);
		if(count > 0){
			//用户保存成功,将激活码保存到HttpSession对象中,用工具类生成六位激活码
			String activeCode = CreateActivatedCodeUtil.createActivatedCode();
			//使用StringRedisTemplate将验证码进行保存
			redisTemplate.opsForValue().set(user.getUserCode(), activeCode);
			//设定redis的最大存活时间位5分钟
			redisTemplate.expire(user.getUserCode(), 5, TimeUnit.MINUTES);
			//判断用户进行的注册方式是邮箱还是手机号码
			if(CheckFormatUtil.checkEmailFormat(user.getUserCode())){
				//是邮箱注册,发送给邮箱激活码
				return mailSenderUtil.sendActivatedCodeMail(user.getUserCode(), activeCode);
			}else if(CheckFormatUtil.checkPhoneFormat(user.getUserCode())){
				//发送手机验证码
				return smsSenderUtil.smsSender(user.getUserCode(), activeCode);
			}
		}
		return false;
	}

	/**
	 * <b>用户账户激活</b>
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean userActivate(User user) throws Exception{
		int count = userDao.updateUserActivateState(user);
		if(count > 0){
			return true;
		}
		return false;
	}

	/**
	 * <b>获得用户激活码</b>
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public String getUserActiveCode(String userCode) throws Exception {
		return redisTemplate.opsForValue().get(userCode);
	}

	/**
	 * <b>查询常用联系人</b>
	 * @param linkUser
	 * @return
	 * @throws Exception
	 */
	public List<ItripUserLinkUser> getLinkUser(ItripUserLinkUser linkUser) throws Exception {
		List<ItripUserLinkUser> linkUserList = new ArrayList<ItripUserLinkUser>();
		linkUserList = userDao.findUserLinkUser(linkUser);
		if(linkUserList != null){
			return linkUserList;
		}
		return new ArrayList<ItripUserLinkUser>();
	}

	/**
	 * <b>添加常用联系人</b>
	 * @param itripUserLinkUser
	 * @return
	 * @throws Exception
	 */
	public int adduserlinkuser(ItripUserLinkUser itripUserLinkUser) throws Exception {
		return userDao.insertUserLinkUser(itripUserLinkUser);
	}



	/**
	 * <b>修改常用联系人信息</b>
	 * @return
	 * @throws Exception
	 */
	public int updateUserLinkUser(ItripAddUserLinkUserVO addUserLinkUserVO) throws Exception {

		int count = 0;
		count = userDao.updateLinkUserInFo(addUserLinkUserVO);
		return count;
	}

	/**
	 * <b>删除常用联系人</b>
	 * @return
	 * @throws Exception
	 */
	public int delUserlinkUser(Integer linkUserId) throws Exception {
		//封装参数
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", linkUserId);

		return userDao.delUserlinkUser(map);
	}
}
