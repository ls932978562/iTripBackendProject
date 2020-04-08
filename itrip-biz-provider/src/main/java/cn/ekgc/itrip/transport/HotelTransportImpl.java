package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.vo.HotCityVo;
import cn.ekgc.itrip.pojo.vo.HotelVo;
import cn.ekgc.itrip.pojo.vo.SearchHotelVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import cn.ekgc.itrip.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <b>爱旅行-酒店信息传输层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelTransport")
@RequestMapping("/hotel/trans")
public class HotelTransportImpl implements HotelTransport {

	@Autowired
	private HotelService hotelService;
	/**
	 * <b>查询热门城市酒店列表</b>
	 * @param hotCityVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/query")
	public List<HotelVo> searchHotelListByHotCity(@RequestBody HotCityVo hotCityVo) throws Exception {
		return hotelService.searchHotelListByHotCity(hotCityVo);
	}

	/**
	 * <b>查询酒店特色、商圈、酒店名称</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/videodesc")
	public Hotel getvideodesc(@RequestParam Long hotelId) throws Exception {
		return hotelService.getvideodesc(hotelId);
	}


	/**
	 * <b>查询酒店分页</b>
	 * @param searchHotelVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/search")
	public Page<HotelVo> searchHotelListByQuery(@RequestBody SearchHotelVO searchHotelVO) throws Exception {
		Page<HotelVo> page = hotelService.searchHotelListByQuery(searchHotelVO);
		return page;
	}

}
