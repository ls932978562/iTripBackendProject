package cn.ekgc.itrip.base.enums;

/**
 * <b>爱旅行-图片类型枚举类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ImgTypeEnum {
	IMG_TYPE_HOTEL(0),
	IMG_TYPE_ROOM(1),
	IMG_TYPE_COMMENT(2),
	;
	private int code;

	ImgTypeEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
