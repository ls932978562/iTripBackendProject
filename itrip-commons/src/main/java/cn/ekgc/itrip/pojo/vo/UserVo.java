package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;

/**
 * <b>爱旅行-注册表单客户输入信息</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userCode;            //用户登录账号
	private String userName;            //用户名
	private String userPassword;        //用户密码

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
