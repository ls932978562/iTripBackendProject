package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Hotelroom;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店房间数据持久层接口</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface HotelroomDao {
	/**
	 * <b>查询酒店房间</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Hotelroom> findHotelroomByQuery(Hotelroom query)throws Exception;

	/**
	 * <b>查询酒店房间临时库存</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer queryTempStore(Map<String, Object> queryMap)throws Exception;

	/**
	 * <b>查询酒店房间总库存</b>
	 * @param queryMap
	 * @return
	 * @throws Exception
	 */
	Integer queryTotalStore(Map<String, Object> queryMap)throws Exception;
}
