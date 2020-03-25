package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.Hotelroom;
import cn.ekgc.itrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>爱旅行-酒店房间传输层接口</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(value = "itrip-biz-provider")
@RequestMapping("/hotelroom/trans")
public interface HotelroomTransport {

	/**
	 * <b>查询酒店房间</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	List<Hotelroom> queryhotelroombyhotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO)throws Exception;

	/**
	 * <b>查询酒店房间库存</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/store")
	int getHotelStore(@RequestBody ValidateRoomStoreVO validateRoomStoreVO)throws Exception;

	/**
	 * <b>根据房间ID查询酒店房间信息</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/room")
	Hotelroom getHotelroom(@RequestParam Long roomId)throws Exception;
}
