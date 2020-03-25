package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.vo.HotCityVo;
import cn.ekgc.itrip.pojo.vo.HotelVo;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>爱旅行-酒店信息传输层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(value = "itrip-biz-provider")
@RequestMapping("/hotel/trans")
public interface HotelTransport {

	/**
	 * <b>查询热门城市酒店列表</b>
	 * @param hotCityVo
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	List<HotelVo> searchHotelListByHotCity(@RequestBody HotCityVo hotCityVo)throws Exception;

	/**
	 * <b>查询酒店特色、商圈、酒店名称</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/videodesc")
	Hotel getvideodesc(@RequestParam Long hotelId)throws Exception;


}
