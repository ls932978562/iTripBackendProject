package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.enums.AreaHotEnum;
import cn.ekgc.itrip.base.enums.ImgTypeEnum;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.entity.AreaDic;
import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.entity.ItripImage;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.pojo.vo.HotCityVo;
import cn.ekgc.itrip.pojo.vo.HotelVo;
import cn.ekgc.itrip.pojo.vo.SearchDetailsHotelVo;
import cn.ekgc.itrip.transport.AreaDicTransport;
import cn.ekgc.itrip.transport.HotelTransport;
import cn.ekgc.itrip.transport.ItripImgTransport;
import cn.ekgc.itrip.transport.LabelDicTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-主业务酒店模块控制器</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelController")
@RequestMapping("/biz/api/hotel")
public class HotelController extends BaseController {

	@Autowired
	private AreaDicTransport areaDicTransport;
	@Autowired
	private LabelDicTransport labelDicTransport;
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private ItripImgTransport itripImgTransport;
	/**
	 * <b>查询热门城市</b>
	 * @param isChina
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotcity/{isChina}")
	public ResponseDto<Object> queryHotCity(@PathVariable("isChina") Integer isChina)throws Exception{
		//新建查询对象
		AreaDic query = new AreaDic();
		//设置查询条件属于国内还是国外
		query.setIsChina(isChina);
		//设置查询城市是热门城市
		query.setIsHot(AreaHotEnum.AREA_HOT_YES.getCode());
		//进行城市列表查询
		List<AreaDic> areaDicList = areaDicTransport.queryHotCity(query);
		return ResponseDto.success(areaDicList);
	}

	/**
	 * <b>查询酒店特色</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotelfeature")
	public ResponseDto<Object> queryhotelfeature()throws Exception{
		//创建查询对象
		LabelDic labelDic = new LabelDic();
		//parentId为16L的属于酒店特色
		labelDic.setParentId(16L);
		//直接进行查询
		List<LabelDic> labelDicList = labelDicTransport.getListByQuery(labelDic);
		//将就点特色的查询结果列表直接返回
		return ResponseDto.success(labelDicList);
	}


	/**
	 * <b>查询酒店特色、商圈、酒店名称</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getvideodesc/{hotelId}")
	public ResponseDto<Object> getvideodesc(@PathVariable("hotelId") Long hotelId)throws Exception{
		Hotel hotel = hotelTransport.getvideodesc(hotelId);
		return ResponseDto.success(hotel);
	}

	/**
	 * <b>根据酒店id查询酒店特色和介绍</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/queryhoteldetails/{hotelId}")
	public ResponseDto<Object> queryhoteldetails(@PathVariable("hotelId") Long hotelId)throws Exception{
		List<SearchDetailsHotelVo> resultList = new ArrayList<SearchDetailsHotelVo>();
		//查询酒店介绍
		Hotel hotel = hotelTransport.getvideodesc(hotelId);
		resultList.add(new SearchDetailsHotelVo("酒店介绍", hotel.getDetails()));
		//查询酒店对应的特色信息列表
		LabelDic labelDicQuery = new LabelDic();
		labelDicQuery.setHotelId(hotelId);
		//在查询时使用左连接连接上itrip_hotel_feature中间表
		List<LabelDic> labelDicList = labelDicTransport.getListByQuery(labelDicQuery);
		if (labelDicList != null && labelDicList.size() > 0) {
			for (LabelDic labelDic : labelDicList) {
				resultList.add(new SearchDetailsHotelVo(labelDic.getName(), labelDic.getDescription()));
			}
		}
		return ResponseDto.success(resultList);
	}

	/**
	 * <b>根据酒店id查询酒店设施</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotelfacilities/{hotelId}")
	public ResponseDto<Object> queryhotelfacilities(@PathVariable("hotelId") Long hotelId)throws Exception{
		Hotel hotel = hotelTransport.getvideodesc(hotelId);
		return ResponseDto.success(hotel.getFacilities());
	}

	/**
	 * <b> 根据酒店id查询酒店政策</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/queryhotelpolicy/{hotelId}")
	public ResponseDto<Object> queryhotelpolicy(@PathVariable("hotelId") Long hotelId)throws Exception{
		Hotel hotel = hotelTransport.getvideodesc(hotelId);
		return ResponseDto.success(hotel.getHotelPolicy());
	}


	@GetMapping("/getimg/{targetId}")
	public ResponseDto<Object> getImg(@PathVariable("targetId") Long targetId)throws Exception{
		//根据酒店Id查询酒店图片
		ItripImage itripImage = new ItripImage();
		itripImage.setTargetId(targetId);
		itripImage.setType(ImgTypeEnum.IMG_TYPE_HOTEL.getCode());
		//根据ItripImage对象查询酒店图片
		List<ItripImage> itripImageList = itripImgTransport.getHotelImg(itripImage);
		return ResponseDto.success(itripImageList);
	}


	/**
	 * <b>查询商圈</b>
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/querytradearea/{cityId}")
	public ResponseDto<Object> queryTradeArea(@PathVariable("cityId") Long cityId)throws Exception{
		HotCityVo hotCityVo = new HotCityVo();
		hotCityVo.setCityId(cityId.intValue());
		List<HotelVo> hotelVoList = hotelTransport.searchHotelListByHotCity(hotCityVo);
		return  ResponseDto.success(hotelVoList);
	}
}











