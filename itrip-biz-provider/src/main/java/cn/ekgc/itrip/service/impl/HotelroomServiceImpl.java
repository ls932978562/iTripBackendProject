package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.enums.OrderStatusEnum;
import cn.ekgc.itrip.dao.HotelOrderDao;
import cn.ekgc.itrip.dao.HotelroomDao;
import cn.ekgc.itrip.pojo.entity.Hotelroom;
import cn.ekgc.itrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import cn.ekgc.itrip.service.HotelroomService;
import cn.ekgc.itrip.transport.HotelTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <b>爱旅行-酒店房间业务层接口实现类</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("hotelroomService")
@Transactional
public class HotelroomServiceImpl implements HotelroomService {

	@Autowired
	private HotelroomDao hotelroomDao;
	@Autowired
	private HotelOrderDao hotelOrderDao;
	@Autowired
	private HotelTransport hotelTransport;
	/**
	 * <b>查询酒店房间</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	public List<Hotelroom> queryhotelroombyhotel(SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		List<Hotelroom> resultList = new ArrayList<Hotelroom>();

		//根据酒店id查询所有的酒店房间
		Hotelroom query = new Hotelroom();
		query.setHotelId(searchHotelRoomVO.getHotelId());
		List<Hotelroom> hotelroomList = hotelroomDao.findHotelroomByQuery(query);
		if (hotelroomList != null && hotelroomList.size() > 0) {
			for (Hotelroom hotelRoom : hotelroomList) {
				// 循环遍历该列表，根据房间id和当前时间查询临时库存数量
				// 封装查询参数
				Map<String, Object> queryMap = new HashMap<String, Object>();
				queryMap.put("roomId", hotelRoom.getId());
				queryMap.put("beginDate", searchHotelRoomVO.getStartDate());
				Integer store = hotelroomDao.queryTempStore(queryMap);

				if (store == null) {
					// 如果临时库存不存在，查询总库存数量
					queryMap.put("productId", hotelRoom.getId());
					store = hotelroomDao.queryTotalStore(queryMap);
				}
				// 计算可用库存，如果库存大于0
				if (store != null && store > 0) {
					// 查询此时该房间订单表中处于未支付和支付成功的订单数量
					Map<String, Object> orderQueryMap = new HashMap<String, Object>();
					orderQueryMap.put("roomId", hotelRoom.getId());
					orderQueryMap.put("startDate", searchHotelRoomVO.getStartDate());
					orderQueryMap.put("endDate", searchHotelRoomVO.getEndDate());
					Integer orderRoomCount = hotelOrderDao.findOrderRoomCountByQuery(orderQueryMap);
					// 使用库存-订单输入，如果大于0则说明该房间可用，那么加入最终的结果列表
					if (store - orderRoomCount > 0) {
						resultList.add(hotelRoom);
					}
				}
			}
		}
		return resultList;
	}


	/**
	 * <b>查询酒店房间库存</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	public int getHotelStore(ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();

		queryMap.put("roomId", validateRoomStoreVO.getRoomId());
		queryMap.put("beginDate", validateRoomStoreVO.getCheckInDate());
		Integer store = hotelroomDao.queryTempStore(queryMap);

		if (store == null) {
			// 如果临时库存不存在，查询总库存数量
			queryMap.put("productId", validateRoomStoreVO.getRoomId());
			store = hotelroomDao.queryTotalStore(queryMap);
		}

		// 计算可用库存，如果库存大于0
		if (store != null && store > 0) {
			// 查询此时该房间订单表中处于未支付和支付成功的订单数量
			Map<String, Object> orderQueryMap = new HashMap<String, Object>();
			orderQueryMap.put("roomId", validateRoomStoreVO.getRoomId());
			orderQueryMap.put("startDate", validateRoomStoreVO.getCheckInDate());
			orderQueryMap.put("endDate", validateRoomStoreVO.getCheckOutDate());
			Integer orderRoomCount = hotelOrderDao.findOrderRoomCountByQuery(orderQueryMap);
			// 使用库存-订单输入，如果大于0则说明该房间可用，那么加入最终的结果列表
			if (store - orderRoomCount > 0) {
				return store - orderRoomCount;
			}
		}

		return 0;
	}

	/**
	 * <b>根据房间ID查询酒店房间信息</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	public Hotelroom getHotelroom(Long roomId) throws Exception {
		Hotelroom hotelroom = new Hotelroom();
		hotelroom.setId(roomId);
		List<Hotelroom> hotelroomList = hotelroomDao.findHotelroomByQuery(hotelroom);
		if(hotelroomList != null){
			return hotelroomList.get(0);
		}
		return hotelroom;
	}
}
