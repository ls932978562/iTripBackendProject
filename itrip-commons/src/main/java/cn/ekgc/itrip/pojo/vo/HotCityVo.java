package cn.ekgc.itrip.pojo.vo;

/**
 * <b>传入的热门城市搜索酒店传入参数VO</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class HotCityVo {

	private Integer cityId;
	private Integer count;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
