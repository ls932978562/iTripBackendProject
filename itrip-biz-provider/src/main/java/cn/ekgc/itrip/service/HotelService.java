package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.vo.HotCityVo;
import cn.ekgc.itrip.pojo.vo.HotelVo;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;

import java.util.List;
/**
 * <b>爱旅行-酒店信息业务层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public interface HotelService {

	/**
	 * <b>查询热门城市酒店列表</b>
	 * @param hotCityVo
	 * @return
	 * @throws Exception
	 */
	List<HotelVo> searchHotelListByHotCity(HotCityVo hotCityVo)throws Exception;

	/**
	 * <b>查询酒店特色、商圈、酒店名称</b>
	 * @return
	 * @throws Exception
	 */
	Hotel getvideodesc(Long hotelId)throws Exception;

}
