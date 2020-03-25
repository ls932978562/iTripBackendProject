package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.pojo.entity.HotelOrder;
import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import cn.ekgc.itrip.pojo.vo.*;
import cn.ekgc.itrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店房间传输层接口实现类</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelOrderTransport")
@RequestMapping("/order/trans")
public class HotelOrderTransportImpl implements HotelOrderTransport{

	@Autowired
	private HotelOrderService hotelOrderService;
	/**
	 * <b>生成订单前,获取预订信息</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	public RoomStoreVO getpreorderinfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		return hotelOrderService.getpreorderinfo(validateRoomStoreVO);
	}

	/**
	 * <b>生成订单</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addOrder")
	public boolean addhotelorder(@RequestBody HotelOrder hotelOrder) throws Exception {
		return hotelOrderService.addhotelorder(hotelOrder);
	}

	/**
	 * <b>查询订单</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getOrder")
	public List<HotelOrder> getHotelOrderByNo(@RequestBody HotelOrder query) throws Exception {
		return hotelOrderService.getHotelOrderByNo(query);
	}


	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/personal")
	public ItripPersonalOrderRoomVO getpersonalorderroominfo(Long orderId) throws Exception {
		return hotelOrderService.getpersonalorderroominfo(orderId);
	}

	/**
	 * <b>查询常用联系人列表</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/link")
	public List<ItripUserLinkUser> getHotelOrderLinkUserListByQuery(HotelOrder queryOrder) throws Exception {
		return hotelOrderService.getHotelOrderLinkUserListByQuery(queryOrder);
	}

	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param itripSearchOrderVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/personalOrderList")
	public Page<HotelOrder> getPersonalOrderList(@RequestBody ItripSearchOrderVO itripSearchOrderVO) throws Exception {
		return hotelOrderService.getPersonalOrderList(itripSearchOrderVO);
	}



	/**
	 * <b>修改交易状态</b>
	 * @param tradeNo
	 * @throws Exception
	 */
	@PostMapping("/updateOrderStatus")
	public int updateOrderByNo(@RequestParam String tradeNo) throws Exception {
		return hotelOrderService.updateOrderByNo(tradeNo);
	}
}
