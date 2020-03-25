package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.enums.OrderStatusEnum;
import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.dao.HotelDao;
import cn.ekgc.itrip.dao.HotelOrderDao;
import cn.ekgc.itrip.dao.HotelroomDao;
import cn.ekgc.itrip.dao.LabelDicDao;
import cn.ekgc.itrip.pojo.entity.*;
import cn.ekgc.itrip.pojo.vo.*;
import cn.ekgc.itrip.service.HotelOrderService;
import cn.ekgc.itrip.service.HotelService;
import cn.ekgc.itrip.service.HotelroomService;
import cn.ekgc.itrip.service.LabelDicService;
import cn.ekgc.itrip.transport.HotelroomTransport;
import cn.ekgc.itrip.util.HotelOrderNoCreater;
import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.InvalidRelationTypeException;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *<b>爱旅行-业务层订单信息接口实现类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("hotelOrderService")
@Transactional
public class HotelOrderServiceImpl implements HotelOrderService {

	@Autowired
	private HotelroomDao hotelroomDao;
	@Autowired
	private HotelOrderDao hotelOrderDao;
	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private HotelService hotelService;
	@Autowired
	private HotelroomTransport hotelroomTransport;
	@Autowired
	private LabelDicDao labelDicDao;
	/**
	 * <b>生成订单前,获取预订信息</b>
	 * @return
	 * @throws Exception
	 */
	public RoomStoreVO getpreorderinfo(ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		RoomStoreVO roomStoreVO = new RoomStoreVO();

		// 封装查询参数,根据房间id和当前时间查询临时库存数量
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

				roomStoreVO.setStore(store - orderRoomCount);

				//根据酒店ID和roomId查询酒店房间价格
				Hotelroom query = new Hotelroom();
				query.setId(validateRoomStoreVO.getRoomId());
				query.setHotelId(validateRoomStoreVO.getHotelId());
				List<Hotelroom> hotelroomList = hotelroomDao.findHotelroomByQuery(query);
				//根据酒店ID和RoomId只能查询到一种类型的房间，获取酒店房间价钱
				roomStoreVO.setPrice(hotelroomList.get(0).getRoomPrice());
				Hotel hotel = new Hotel();
				hotel.setId(validateRoomStoreVO.getHotelId());
				List<Hotel> hotelList = hotelDao.findHotelListByQuery(hotel);
				//只能查到一个酒店
				roomStoreVO.setHotelName(hotelList.get(0).getHotelName());
				roomStoreVO.setCount(validateRoomStoreVO.getCount());
				roomStoreVO.setHotelId(validateRoomStoreVO.getHotelId());
				roomStoreVO.setRoomId(validateRoomStoreVO.getRoomId());
				roomStoreVO.setCheckInDate(validateRoomStoreVO.getCheckInDate());
				roomStoreVO.setCheckOutDate(validateRoomStoreVO.getCheckOutDate());
			}
		}
		return roomStoreVO;
	}


	/**
	 * <b>生成订单</b>
	 * @return
	 * @throws Exception
	 */
	public boolean addhotelorder(HotelOrder hotelOrder) throws Exception {
		//将订单信息添加到数据库
		int count = hotelOrderDao.saveHotelOrder(hotelOrder);
		if(count > 0){
			return true;
		}
		return false;
	}


	/**
	 * <b>查询订单</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<HotelOrder> getHotelOrderByNo(HotelOrder query) throws Exception {
		List<HotelOrder> hotelOrderList = new ArrayList<HotelOrder>();
		hotelOrderList = hotelOrderDao.findHotelOrder(query);
		if(hotelOrderList != null){
			return hotelOrderList;
		}
		return hotelOrderList;
	}



	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public ItripPersonalOrderRoomVO getpersonalorderroominfo(Long orderId) throws Exception {
		ItripPersonalOrderRoomVO personalOrderRoomVO = new ItripPersonalOrderRoomVO();

		HotelOrder query = new HotelOrder();
		query.setId(orderId);
		//查询订单列表

		List<HotelOrder> hotelOrderList = hotelOrderDao.findHotelOrder(query);
		//只能查到一个订单
		HotelOrder order = hotelOrderList.get(0);
		if(hotelOrderList != null && hotelOrderList.size() > 0){
			personalOrderRoomVO.setId(order.getId());
			personalOrderRoomVO.setHotelId(order.getHotelId());
			personalOrderRoomVO.setHotelName(order.getHotelName());

			//根据酒店ID和roomId查询信息
			Hotel hotel = new Hotel();
			hotel.setId(order.getHotelId());
			List<Hotel> hotelList = hotelDao.findHotelListByQuery(hotel);

			//根据roomId查询房间信息
			Hotelroom hotelroom = new Hotelroom();
			hotelroom.setId(order.getRoomId());
			List<Hotelroom> hotelroomList = hotelroomDao.findHotelroomByQuery(hotelroom);

			personalOrderRoomVO.setHotelLevel(hotelList.get(0).getHotelLevel());
			personalOrderRoomVO.setAddress(hotelList.get(0).getAddress());
			personalOrderRoomVO.setRoomId(order.getRoomId());
			personalOrderRoomVO.setRoomTitle(hotelroomList.get(0).getRoomTitle());
			personalOrderRoomVO.setRoomBedTypeId(hotelroomList.get(0).getRoomBedTypeId());
			personalOrderRoomVO.setCheckInDate(order.getCheckInDate());
			personalOrderRoomVO.setCheckOutDate(order.getCheckOutDate());
			personalOrderRoomVO.setCount(order.getCount());
			personalOrderRoomVO.setBookingDays(order.getBookingDays());
			personalOrderRoomVO.setLinkUserName(order.getLinkUserName());
			personalOrderRoomVO.setSpecialRequirement(order.getSpecialRequirement());
			personalOrderRoomVO.setPayAmount(order.getPayAmount());
			personalOrderRoomVO.setRoomPayType(order.getPayType());
			personalOrderRoomVO.setIsHavingBreakfast(hotelroomList.get(0).getIsHavingBreakfast());

			//查询字典表中的名字
			LabelDic labelDic = new LabelDic();
			labelDic.setId(hotelroomList.get(0).getRoomBedTypeId());
			List<LabelDic> labelDicList = labelDicDao.findLabelDicByQuery(labelDic);
			personalOrderRoomVO.setRoomBedTypeName(labelDicList.get(0).getName());


		}
		return personalOrderRoomVO;
	}

	/**
	 * <b>查询常用联系人列表</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ItripUserLinkUser> getHotelOrderLinkUserListByQuery(HotelOrder queryOrder) throws Exception {
		List<ItripUserLinkUser> itripUserLinkUserList = hotelOrderDao.findOrderLinkUserByQuery(queryOrder);
		if(itripUserLinkUserList != null && itripUserLinkUserList.size() > 0){
			return itripUserLinkUserList;
		}
		return new ArrayList<ItripUserLinkUser>();
	}



	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param itripSearchOrderVO
	 * @return
	 * @throws Exception
	 */
	public Page<HotelOrder> getPersonalOrderList(ItripSearchOrderVO itripSearchOrderVO) throws Exception {
		Page<HotelOrder> page = new Page<HotelOrder>();
		List<HotelOrder> hotelOrderList = new ArrayList<HotelOrder>();

		if(itripSearchOrderVO.getOrderType() == -1){
			itripSearchOrderVO.setOrderType(null);
		}
		if(itripSearchOrderVO.getOrderStatus() == -1){
			itripSearchOrderVO.setOrderStatus(null);
		}

		//封装查询参数对象
		HotelOrder hotelOrder = new HotelOrder();
		hotelOrder.setUserId(itripSearchOrderVO.getUserId());
		hotelOrder.setOrderNo(itripSearchOrderVO.getOrderNo());
		hotelOrder.setLinkUserName(itripSearchOrderVO.getLinkUserName());
		hotelOrder.setCheckInDate(itripSearchOrderVO.getStartDate());
		hotelOrder.setCheckOutDate(itripSearchOrderVO.getEndDate());
		hotelOrder.setOrderStatus(itripSearchOrderVO.getOrderStatus());
		hotelOrder.setOrderType(itripSearchOrderVO.getOrderType());

		//设置开始分页
		if(itripSearchOrderVO.getPageNo() == null){
			itripSearchOrderVO.setPageNo(1);
		}
		PageHelper.startPage(itripSearchOrderVO.getPageNo(), itripSearchOrderVO.getPageSize());

		//进行查询

		hotelOrderList = hotelOrderDao.findHotelOrder(hotelOrder);
		if(hotelOrderList != null && hotelOrderList.size() > 0){
			PageInfo<HotelOrder> pageInfo = new PageInfo<HotelOrder>(hotelOrderList);

			page.setCurPage(itripSearchOrderVO.getPageNo());
			page.setPageCount(pageInfo.getPages());
			page.setTotal((int)pageInfo.getTotal());
			page.setRows(hotelOrderList);
			page.setBeginPos(pageInfo.getStartRow());

			return page;
		}
		return new Page<HotelOrder>();
	}



	/**
	 * <b>修改交易状态</b>
	 * @param tradeNo
	 * @throws Exception
	 */
	public int updateOrderByNo(String tradeNo) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("tradeNo", tradeNo);
		map.put("tradeStatus", OrderStatusEnum.ORDER_STATUS_PAYED.getCode());
		return hotelOrderDao.updateOrderStatus(map);
	}
}
