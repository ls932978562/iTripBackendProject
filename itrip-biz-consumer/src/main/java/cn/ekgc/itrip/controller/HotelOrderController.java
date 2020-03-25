package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.enums.OrderStatusEnum;
import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.entity.*;
import cn.ekgc.itrip.pojo.vo.*;
import cn.ekgc.itrip.transport.HotelOrderTransport;
import cn.ekgc.itrip.transport.HotelTransport;
import cn.ekgc.itrip.transport.HotelroomTransport;
import cn.ekgc.itrip.transport.UserTransport;
import cn.ekgc.itrip.util.HotelOrderNoCreater;
import cn.ekgc.itrip.util.TimeFormatUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.*;

/**
 * <b>爱旅行-酒店订单控制器</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelOrderController")
@RequestMapping("/biz/api/hotelorder")
public class HotelOrderController extends BaseController {

	@Autowired
	private HotelOrderTransport hotelOrderTransport;
	@Autowired
	private HotelroomTransport hotelroomTransport;
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private UserTransport userTransport;

	/**
	 * <b>生成订单前,获取预订信息</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getpreorderinfo")
	public ResponseDto<Object> getpreorderinfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO)throws Exception{
		//查询对应日期的房间剩余数量
		RoomStoreVO roomStoreVO = hotelOrderTransport.getpreorderinfo(validateRoomStoreVO);
		return ResponseDto.success(roomStoreVO);
	}

	/**
	 * <b>生成订单</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addhotelorder")
	public ResponseDto<Object> addhotelOrder(@RequestBody ItripAddHotelOrderVO addHotelOrderVO)throws Exception{
		HotelOrder hotelOrder = new HotelOrder();
		Map<String,String> map = new HashMap<String, String>();

		//生成订单编号
		String orderNo = HotelOrderNoCreater.createOrderNo(addHotelOrderVO.getRoomId(), addHotelOrderVO.getHotelId());

		//封装查询酒店房间库存的对象，进行查询酒店库存
		ValidateRoomStoreVO validateRoomStoreVO = new ValidateRoomStoreVO();
		BeanUtils.copyProperties(addHotelOrderVO, validateRoomStoreVO);

		//获得酒店房间库存
		int count = hotelroomTransport.getHotelStore(validateRoomStoreVO);

		//判断酒店房间库存大于要预定的酒店房间
		if(count > addHotelOrderVO.getCount()){

			//将入驻人员信息进行格式改变
			StringBuffer sb = new StringBuffer();
			List<ItripUserLinkUser> list = addHotelOrderVO.getLinkUser();
			for(ItripUserLinkUser linkUser : list){
				sb.append(linkUser.getLinkUserName() + ",");
			}
			String userLinkUser = sb.toString();
			userLinkUser = userLinkUser.substring(0, userLinkUser.length()-1);

			//获得Cookie对象
			Cookie cookies[] = request.getCookies();
			String  userCode = "";
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("user")){
					userCode = cookie.getValue();
				}
			}
			User user = new User();
			user.setUserCode(userCode);
			List<User> userList = userTransport.getUserByQuery(user);

			//计算预定天数
			Long days = (addHotelOrderVO.getCheckOutDate().getTime() - addHotelOrderVO.getCheckInDate().getTime())/1000/60/60/24;
			//根据房间Id查询房间信息
			Hotelroom hotelroom = hotelroomTransport.getHotelroom(addHotelOrderVO.getRoomId());

			Double payAmount = days * hotelroom.getRoomPrice();

			//封装酒店订单信息
			hotelOrder.setUserId(userList.get(0).getId());
			hotelOrder.setOrderType(addHotelOrderVO.getOrderType());
			hotelOrder.setOrderNo(orderNo);
			hotelOrder.setTradeNo(orderNo);
			hotelOrder.setHotelId(addHotelOrderVO.getHotelId());
			hotelOrder.setHotelName(addHotelOrderVO.getHotelName());
			hotelOrder.setRoomId(addHotelOrderVO.getRoomId());
			hotelOrder.setCount(addHotelOrderVO.getCount());
			hotelOrder.setBookingDays(days.intValue());
			hotelOrder.setCheckInDate(addHotelOrderVO.getCheckInDate());
			hotelOrder.setCheckOutDate(addHotelOrderVO.getCheckOutDate());
			hotelOrder.setOrderStatus(OrderStatusEnum.ORDER_STATUS_PREPAY.getCode());
			hotelOrder.setPayAmount(payAmount);
			hotelOrder.setNoticePhone(addHotelOrderVO.getNoticePhone());
			hotelOrder.setNoticeEmail(addHotelOrderVO.getNoticeEmail());
			hotelOrder.setSpecialRequirement(addHotelOrderVO.getSpecialRequirement());
			hotelOrder.setIsNeedInvoice(addHotelOrderVO.getIsNeedInvoice());
			hotelOrder.setInvoiceType(addHotelOrderVO.getInvoiceType());
			hotelOrder.setInvoiceHead(addHotelOrderVO.getInvoiceHead());
			hotelOrder.setLinkUserName(userLinkUser);
			hotelOrder.setCreationDate(new Date());

			//进行保存
			hotelOrderTransport.addhotelorder(hotelOrder);
			//根据订单编号查询订单
			HotelOrder query = new HotelOrder();
			query.setOrderNo(orderNo);
			List<HotelOrder> orderList = hotelOrderTransport.getHotelOrderByNo(query);
			map.put("id", String.valueOf(orderList.get(0).getId()));
			map.put("orderNo", orderList.get(0).getOrderNo());
			return ResponseDto.success(map);
		}
		return null;
	}


	/**
	 * <b> 根据订单ID查看个人订单详情</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getpersonalorderinfo/{orderId}")
	public ResponseDto<Object> getpersonalorderinfo(@PathVariable("orderId") Long orderId)throws Exception{
		ItripPersonalHotelOrderVO personalHotelOrderVO = new ItripPersonalHotelOrderVO();
		//根据订单Id查询订单信息
		HotelOrder hotelOrder = new HotelOrder();
		hotelOrder.setId(orderId);
		//进行查询
		List<HotelOrder> orderList = hotelOrderTransport.getHotelOrderByNo(hotelOrder);

		return  ResponseDto.success(orderList.get(0));

	}


	/**
	 * <b>根据订单ID查看个人订单详情-房型相关信息</b>
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getpersonalorderroominfo/{orderId}")
	public ResponseDto<Object> getpersonalorderroominfo(@PathVariable("orderId") Long orderId)throws Exception{
		//根据订单Id查询订单信息
		ItripPersonalOrderRoomVO personalOrderRoomVO = hotelOrderTransport.getpersonalorderroominfo(orderId);
		return ResponseDto.success(personalOrderRoomVO);

	}

	@GetMapping("/queryOrderById/{orderId}")
	public ResponseDto<Object> queryOrderById(@PathVariable("orderId") Long orderId)throws Exception{
		ModifyHotelOrderVO modifyHotelOrderVO = new ModifyHotelOrderVO();
		//根据订单Id查询订单信息
		HotelOrder query = new HotelOrder();
		query.setId(orderId);
		//查询订单列表
		List<HotelOrder> hotelOrderList = hotelOrderTransport.getHotelOrderByNo(query);
		HotelOrder order = hotelOrderList.get(0);
		modifyHotelOrderVO.setId(order.getId());
		modifyHotelOrderVO.setPayType(order.getPayType());
		modifyHotelOrderVO.setOrderType(order.getOrderType());
		modifyHotelOrderVO.setOrderNo(order.getOrderNo());
		modifyHotelOrderVO.setHotelId(order.getHotelId());
		modifyHotelOrderVO.setHotelName(order.getHotelName());
		modifyHotelOrderVO.setRoomId(order.getRoomId());
		modifyHotelOrderVO.setCount(order.getCount());
		modifyHotelOrderVO.setCheckInDate(order.getCheckInDate());
		modifyHotelOrderVO.setCheckOutDate(order.getCheckOutDate());
		modifyHotelOrderVO.setNoticePhone(order.getNoticePhone());
		modifyHotelOrderVO.setNoticeEmail(order.getNoticeEmail());
		modifyHotelOrderVO.setSpecialRequirement(order.getSpecialRequirement());
		modifyHotelOrderVO.setIsNeedInvoice(order.getIsNeedInvoice());
		modifyHotelOrderVO.setInvoiceType(order.getInvoiceType());
		modifyHotelOrderVO.setInvoiceHead(order.getInvoiceHead());
		modifyHotelOrderVO.setLinkUserName(order.getLinkUserName());
		modifyHotelOrderVO.setBookType(order.getBookType());

		//根据订单编号查询联系人列表
		HotelOrder queryOrder = new HotelOrder();
		queryOrder.setId(orderId);
		List<ItripOrderLinkUserVo> orderLinkUserVoList = new ArrayList<ItripOrderLinkUserVo>();
		List<ItripUserLinkUser> linkUserList = hotelOrderTransport.getHotelOrderLinkUserListByQuery(queryOrder);
		for(ItripUserLinkUser itripUserLinkUser : linkUserList){
			ItripOrderLinkUserVo itripOrderLinkUserVo = new ItripOrderLinkUserVo();
			BeanUtils.copyProperties(itripUserLinkUser, itripOrderLinkUserVo);
			orderLinkUserVoList.add(itripOrderLinkUserVo);
		}
		modifyHotelOrderVO.setItripOrderLinkUserList(orderLinkUserVoList);
		return ResponseDto.success(modifyHotelOrderVO);
	}


	/**
	 * <b>查询个人订单列表，并分页显示</b>
	 * @param itripSearchOrderVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getpersonalorderlist")
	public ResponseDto<Object> getpersonalorderlist(@RequestBody ItripSearchOrderVO itripSearchOrderVO)throws Exception{

		//从cookie中获取当前登录用户
		Cookie[] cookies = request.getCookies();
		String userCode = "";
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")){
				userCode = cookie.getValue();
			}
		}

		//根据用户UserCode查询用户ID
		User user = new User();
		user.setUserCode(userCode);
		List<User> userList = userTransport.getUserByQuery(user);
		itripSearchOrderVO.setUserId(userList.get(0).getId());

		Page<HotelOrder> page = hotelOrderTransport.getPersonalOrderList(itripSearchOrderVO);
		return ResponseDto.success(page);
	}
}
