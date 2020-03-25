package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.Hotelroom;
import cn.ekgc.itrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;

import java.util.List;

/**
 * <b>爱旅行-酒店房间业务层接口</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */

public interface HotelroomService {
	/**
	 * <b>查询酒店房间</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	List<Hotelroom> queryhotelroombyhotel(SearchHotelRoomVO searchHotelRoomVO)throws Exception;


	/**
	 * <b>查询酒店房间库存</b>
	 * @param validateRoomStoreVO
	 * @return
	 * @throws Exception
	 */
	int getHotelStore(ValidateRoomStoreVO validateRoomStoreVO)throws Exception;


	/**
	 * <b>根据房间ID查询酒店房间信息</b>
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	Hotelroom getHotelroom(Long roomId)throws Exception;
}
