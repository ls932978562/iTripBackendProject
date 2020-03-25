package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Hotel;

import java.util.List;
/**
 * <b>爱旅行-酒店信息数据持久层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public interface HotelDao {

	/**
	 * <b>查询热门城市酒店列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Hotel> findHotelListByQuery(Hotel query)throws Exception;
}
