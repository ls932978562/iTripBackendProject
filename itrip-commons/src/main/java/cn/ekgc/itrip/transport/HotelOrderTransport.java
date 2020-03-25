package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.pojo.entity.HotelOrder;
import cn.ekgc.itrip.pojo.entity.ItripUserLinkUser;
import cn.ekgc.itrip.pojo.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/order/trans")
public interface HotelOrderTransport {

	/**
	 * <b>生成订单前,获取预订信息</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	RoomStoreVO getpreorderinfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO)throws Exception;


	/**
	 * <b>生成订单</b>
	 * @param hotelOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addOrder")
	boolean addhotelorder(@RequestBody HotelOrder hotelOrder)throws Exception;

	/**
	 * <b>查询订单</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getOrder")
	List<HotelOrder> getHotelOrderByNo(@RequestBody HotelOrder query)throws Exception;


	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/personal")
	ItripPersonalOrderRoomVO getpersonalorderroominfo(@RequestParam Long orderId)throws Exception;

	/**
	 * <b>查询常用联系人列表</b>
	 * @param queryOrder
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/link")
	List<ItripUserLinkUser> getHotelOrderLinkUserListByQuery(@RequestBody HotelOrder queryOrder)throws Exception;


	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param itripSearchOrderVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/personalOrderList")
	Page<HotelOrder> getPersonalOrderList(@RequestBody ItripSearchOrderVO itripSearchOrderVO)throws Exception;


	/**
	 * <b>修改交易状态</b>
	 * @param tradeNo
	 * @throws Exception
	 */
	@PostMapping("/updateOrderStatus")
	int updateOrderByNo(@RequestParam String tradeNo)throws Exception;
}
