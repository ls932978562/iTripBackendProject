package cn.ekgc.itrip.base.enums;

/**
 * <b>爱旅行-用户注册类型枚举类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public enum UserRegistryTypeEnum {
	USER_TYPE_REG(0),
	USER_TYPE_WX(1),
	USER_TYPE_QQ(2),
	USER_TYPE_WB(3)
	;
	private int code;

	private UserRegistryTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
