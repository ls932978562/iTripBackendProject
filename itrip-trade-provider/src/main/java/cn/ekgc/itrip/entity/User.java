package cn.ekgc.itrip.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
@ApiModel("用户信息实体类")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户主键")
	private Long id;
	@ApiModelProperty(value = "用户姓名")
	private String username;
	@ApiModelProperty(value = "用户电话")
	private String cellphone;
	@ApiModelProperty(value = "用户密码")
	private String password;
	@ApiModelProperty(value = "出生日期",required = false)  //设置成false表示可有可无
	private Date birthday;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


}
