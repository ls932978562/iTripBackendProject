package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.Hotelroom;
import cn.ekgc.itrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import cn.ekgc.itrip.service.HotelroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>爱旅行-酒店房间传输层接口实现类</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelroomTransport")
@RequestMapping("/hotelroom/trans")
public class HotelroomTransportImpl implements HotelroomTransport{

	@Autowired
	private HotelroomService hotelroomService;

	/**
	 * <b>查询酒店房间</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	public List<Hotelroom> queryhotelroombyhotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		return hotelroomService.queryhotelroombyhotel(searchHotelRoomVO);
	}

	/**
	 * <b>查询酒店房间库存</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/store")
	public int getHotelStore(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		return hotelroomService.getHotelStore(validateRoomStoreVO);
	}

	/**
	 * <b>根据房间ID查询酒店房间信息</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/room")
	public Hotelroom getHotelroom(@RequestParam Long roomId) throws Exception {
		return hotelroomService.getHotelroom(roomId);
	}
}
