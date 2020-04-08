package cn.ekgc.itrip.pojo.vo;

import java.io.Serializable;

/**
 * <b>爱旅行-酒店详情视图对象</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class SearchDetailsHotelVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String description;

	public SearchDetailsHotelVo() {}

	public SearchDetailsHotelVo(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}