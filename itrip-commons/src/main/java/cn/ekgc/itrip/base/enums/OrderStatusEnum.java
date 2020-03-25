package cn.ekgc.itrip.base.enums;
/**
 * <b>爱旅行-订单状态枚举类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public enum OrderStatusEnum {

	ORDER_STATUS_PREPAY(0),
	ORDER_STATUS_CANCEL(1),
	ORDER_STATUS_PAYED(2),
	ORDER_STATUS_SUCCESS(3),
	ORDER_STATUS_COMMENT(4)
	;
	private int code;

	OrderStatusEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
