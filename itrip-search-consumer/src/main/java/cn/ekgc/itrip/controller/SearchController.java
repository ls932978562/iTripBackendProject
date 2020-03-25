package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.vo.HotCityVo;
import cn.ekgc.itrip.pojo.vo.HotelVo;
import cn.ekgc.itrip.transport.HotelTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-搜索模块控制器</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("searchController")
@RequestMapping("/search/api/hotellist")
public class SearchController extends BaseController {

	@Autowired
	private HotelTransport hotelTransport;

	@PostMapping("/searchItripHotelListByHotCity")
	public ResponseDto searchItripHotelListByHotCity(@RequestBody HotCityVo hotCityVo)throws Exception{
		if(hotCityVo.getCityId() != null){
			//进行查询
			List<HotelVo> hotelList = hotelTransport.searchHotelListByHotCity(hotCityVo);
			return ResponseDto.success(hotelList);
		}else {
			return ResponseDto.failure("错误码：" + "\n" + "20004: 城市id不能为空");
		}
	}
}












