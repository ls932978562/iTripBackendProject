package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.HotelOrder;
import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店订单数据持久层接口</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface HotelOrderDao {
	/**
	 * <b>查询酒店房间订单中未支付和已支付的数量</b>
	 * @param orderMap
	 * @return
	 * @throws Exception
	 */
	Integer findOrderRoomCountByQuery(Map<String, Object> orderMap)throws Exception;


	/**
	 * <b>生成订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	int saveHotelOrder(HotelOrder hotelOrder)throws Exception;


	/**
	 * <b>查询订单</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder> findHotelOrder(HotelOrder query)throws Exception;


	/**
	 * <b>查询常用联系人列表</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	List<ItripUserLinkUser> findOrderLinkUserByQuery(HotelOrder queryOrder)throws Exception;


	/**
	 * <b>修改交易状态</b>
	 * @param map
	 * @throws Exception
	 */
	int updateOrderStatus(Map<String, Object> map)throws Exception;
}
