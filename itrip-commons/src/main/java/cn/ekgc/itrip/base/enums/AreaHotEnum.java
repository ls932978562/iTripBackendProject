package cn.ekgc.itrip.base.enums;

/**
 * <b>爱旅行-热门城市枚举类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public enum AreaHotEnum {
	AREA_HOT_YES(1),
	AREA_HOT_NO(0)
	;
	private int code;

	AreaHotEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
