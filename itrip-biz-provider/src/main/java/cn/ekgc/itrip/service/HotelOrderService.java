package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.pojo.entity.HotelOrder;
import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import cn.ekgc.itrip.pojo.vo.*;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店订单业务层接口</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */

public interface HotelOrderService {

	/**
	 * <b>生成订单前,获取预订信息</b>
	 * @return
	 * @throws Exception
	 */
	RoomStoreVO getpreorderinfo(ValidateRoomStoreVO validateRoomStoreVO) throws Exception;

	/**
	 * <b>生成订单</b>
	 * @return
	 * @throws Exception
	 */
	boolean addhotelorder(HotelOrder hotelOrder)throws Exception;


	/**
	 * <b>查询订单</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<HotelOrder> getHotelOrderByNo(HotelOrder query)throws Exception;


	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	ItripPersonalOrderRoomVO getpersonalorderroominfo(Long orderId)throws Exception;


	/**
	 * <b>查询常用联系人列表</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	List<ItripUserLinkUser> getHotelOrderLinkUserListByQuery(HotelOrder queryOrder)throws Exception;


	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param itripSearchOrderVO
	 * @return
	 * @throws Exception
	 */
	Page<HotelOrder> getPersonalOrderList(ItripSearchOrderVO itripSearchOrderVO)throws Exception;


	/**
	 * <b>修改交易状态</b>
	 * @param tradeNo
	 * @throws Exception
	 */
	int updateOrderByNo(String tradeNo)throws Exception;

	/**
	 * <b>修改交易状态</b>
	 * @param orderId
	 * @throws Exception
	 */
	int updateOrderById(Long orderId)throws Exception;
}
