package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.enums.ImgTypeEnum;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.entity.Hotelroom;
import cn.ekgc.itrip.pojo.entity.ItripImage;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.itrip.transport.HotelroomTransport;
import cn.ekgc.itrip.transport.ItripImgTransport;
import cn.ekgc.itrip.transport.LabelDicTransport;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-酒店房间控制器</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelroomController")
@RequestMapping("/biz/api/hotelroom")
public class HotelroomController extends BaseController {

	@Autowired
	private HotelroomTransport hotelroomTransport;
	@Autowired
	private LabelDicTransport labelDicTransport;
	@Autowired
	private ItripImgTransport itripImgTransport;

	/**
	 * <b>查询酒店房间</b>
	 * @param searchHotelRoomVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryhotelroombyhotel")
	public ResponseDto<Object> queryhotelroombyhotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO)throws Exception{
		List<List<Hotelroom>> resultList = new ArrayList<List<Hotelroom>>();
		//直接查询酒店房间列表

		List<Hotelroom> hotelroomList  = hotelroomTransport.queryhotelroombyhotel(searchHotelRoomVO);
		// 循环遍历集合
		if (hotelroomList != null && hotelroomList.size() > 0) {
			for (Hotelroom hotelroom : hotelroomList) {
				List<Hotelroom> list = new ArrayList<Hotelroom>();
				//将集合中的每一项加到大的集合中
				list.add(hotelroom);
				resultList.add(list);
			}
		}
		return ResponseDto.success(resultList);
	}

	/**
	 * <b>查询酒店床型</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/queryhotelroombed")
	public ResponseDto<Object> queryhotelroombed()throws Exception{
		//封装查询对象，在itrip_label_dic表中进行查询
		LabelDic query = new LabelDic();
		query.setParentId(1L);
		return ResponseDto.success(labelDicTransport.getListByQuery(query));
	}

	/**
	 * <b>根据targetId查询酒店图片(type=0)</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getimg/{targetId}")
	public ResponseDto<Object> getHotelImg(@PathVariable("targetId") Long targetId)throws Exception{
		//封装查询对象
		ItripImage query = new ItripImage();
		query.setTargetId(targetId);
		query.setType(String.valueOf(ImgTypeEnum.IMG_TYPE_HOTEL.getCode()));
		List<ItripImage> itripImageList = itripImgTransport.getHotelImg(query);
		return ResponseDto.success(itripImageList);
	}
}










