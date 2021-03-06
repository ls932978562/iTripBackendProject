package cn.ekgc.itrip.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 *<b>爱旅行-用户模块实体类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@ApiModel(value = "用户信息实体类 User",description = "用户信息")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户主键")
	private Long id;                        //用户主键
	@ApiModelProperty(value = "用户注册账号邮箱或手机号")
	private String userCode;                //注册邮箱或手机号
	@ApiModelProperty(value = "用户密码")
	private String userPassword;            //用户登录密码
	@ApiModelProperty(value = "用户类型")
	private Integer userType;               //用户类型
	@ApiModelProperty(value = "平台id")
	private Long flatID;                    //平台ID
	@ApiModelProperty(value = "用户姓名")
	private String userName;                //用户姓名
	@ApiModelProperty(value = "weChat号码")
	private String weChat;                  //weChat号码
	@ApiModelProperty(value = "QQ号码")
	private String QQ;                      //QQ号码
	@ApiModelProperty(value = "微博账号")
	private String weibo;                   //微博账号
	@ApiModelProperty(value = "百度账号")
	private String baidu;                   //百度账号
	@ApiModelProperty(value = "是否激活")
	private int activated;                  //是否激活
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date creationDate;
	private Long createdBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date modifyDate;
	private Long modifiedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getFlatID() {
		return flatID;
	}

	public void setFlatID(Long flatID) {
		this.flatID = flatID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String QQ) {
		this.QQ = QQ;
	}

	public String getWeibo() {
		return weibo;
	}

	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}

	public String getBaidu() {
		return baidu;
	}

	public void setBaidu(String baidu) {
		this.baidu = baidu;
	}

	public int getActivated() {
		return activated;
	}

	public void setActivated(int activated) {
		this.activated = activated;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
