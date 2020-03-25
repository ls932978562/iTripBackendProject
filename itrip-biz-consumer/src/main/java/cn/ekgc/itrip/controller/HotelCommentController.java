package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import cn.ekgc.itrip.base.enums.ImgTypeEnum;
import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.entity.*;
import cn.ekgc.itrip.pojo.vo.ItripAddCommentVO;
import cn.ekgc.itrip.pojo.vo.ListCommentVO;
import cn.ekgc.itrip.pojo.vo.ScoreCommentVO;
import cn.ekgc.itrip.pojo.vo.SearchCommentVO;
import cn.ekgc.itrip.transport.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店评论控制器</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelCommentController")
@RequestMapping("/biz/api/comment")
public class HotelCommentController extends BaseController {

	@Autowired
	private HotelCommentTransport hotelCommentTransport;
	@Autowired
	private ItripImgTransport itripImgTransport;
	@Autowired
	private HotelTransport hotelTransport;
	@Autowired
	private LabelDicTransport labelDicTransport;
	@Autowired
	private UserTransport userTransport;
	/**
	 * <b> 据酒店id查询酒店平均分</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/gethotelscore/{hotelId}")
	public ResponseDto<Object> gethotelscore(@PathVariable("hotelId") Long hotelId)throws Exception{
		//封装查询对象进行查询酒店积分
		HotelComment hotelComment = new HotelComment();
		hotelComment.setHotelId(hotelId);
		//进行结果查询
		ScoreCommentVO scoreCommentVO = hotelCommentTransport.getHotelScore(hotelComment);
		return ResponseDto.success(scoreCommentVO);
	}


	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param searchCommentVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getcommentlist")
	public ResponseDto<Object> getcommentlist(@RequestBody SearchCommentVO searchCommentVO)throws Exception{
		if(searchCommentVO.getIsHavingImg() == -1){
			searchCommentVO.setIsHavingImg(null) ;
		}
		if(searchCommentVO.getIsOk() == -1){
			searchCommentVO.setIsOk(null) ;
		}

		//根据查询条件进行查询评论列表
		Page<HotelComment> page = hotelCommentTransport.getcommentlist(searchCommentVO);
		return ResponseDto.success(page);
	}


	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param hotelId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getcount/{hotelId}")
	public ResponseDto<Object> getCommentCounts(@PathVariable("hotelId") Long hotelId)throws Exception{
		//前端需要返回一个map集合
		Map<String,Integer> countMap = new HashMap<String,Integer>();
		Map<String,Object> map = new HashMap<String,Object>();
		Integer count = 0;
		if(hotelId != null && !"".equals(hotelId)){
			//根据酒店Id查询评论数量,封装查询参数
			//查询总数量
			map.put("hotelId", hotelId);
			count = hotelCommentTransport.getCommentCounts(map);
			if (count != null) {
				countMap.put("allcomment",count);
			}

			//查询值得推荐数量
			map.put("isOk",1);
			count = hotelCommentTransport.getCommentCounts(map);
			if (count != null) {
				countMap.put("isok",count);
			}

			//查询有待改善数量
			map.put("isOk",0);
			count = hotelCommentTransport.getCommentCounts(map);
			if (count != null) {
				countMap.put("improve",count);
			}

			//查询有图片数量
			map.put("isHavingImg", 1);
			//将isOk设置为null
			map.put("isOk", null);
			count = hotelCommentTransport.getCommentCounts(map);
			if (count != null) {
				countMap.put("havingimg",count);
			}

		}
		return ResponseDto.success(countMap);
	}


	/**
	 * <b>查询评论图片</b>
	 * @param targetId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/getimg/{targetId}")
	public ResponseDto<Object> getCommentImage(@PathVariable("targetId") Long targetId)throws Exception{
		//封装查询对象
		ItripImage query = new ItripImage();
		query.setTargetId(targetId);
		query.setType(String.valueOf(ImgTypeEnum.IMG_TYPE_COMMENT.getCode()));
		List<ItripImage> itripImageList = itripImgTransport.getHotelImg(query);
		return ResponseDto.success(itripImageList);
	}


	/**
	 * <b> 获取酒店相关信息（酒店名称、酒店星级）</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/gethoteldesc/{hotelId}")
	public ResponseDto<Object> gethoteldesc(@PathVariable("hotelId") Long hotelId)throws Exception{
		//根据酒店Id查询酒店信息
		Hotel hotel = hotelTransport.getvideodesc(hotelId);
		return ResponseDto.success(hotel);
	}

	@GetMapping("/gettraveltype")
	public ResponseDto<Object> gettraveltype()throws Exception{
		//查询出游类型在itrip_label_dic中查父级是107的
		LabelDic labelDic = new LabelDic();
		labelDic.setParentId(107L);
		List<LabelDic> labelDicList = labelDicTransport.getListByQuery(labelDic);
		return ResponseDto.success(labelDicList);
	}

	/**
	 * <b>新增评论</b>
	 * @param itripAddCommentVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/add")
	public ResponseDto<Object> add(@RequestBody ItripAddCommentVO itripAddCommentVO)throws Exception{
		HotelComment comment = new HotelComment();
		comment.setHotelId(itripAddCommentVO.getHotelId());
		comment.setProductId(itripAddCommentVO.getProductId());
		comment.setOrderId(itripAddCommentVO.getOrderId());
		comment.setProductType(itripAddCommentVO.getProductType());
		comment.setContent(itripAddCommentVO.getContent());


		//从cookie中获取当前登录用户
		Cookie[] cookies = request.getCookies();
		String userCode = "";
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")){
				userCode = cookie.getValue();
			}
		}
		User query = new User();
		query.setUserCode(userCode);
		List<User> userList = userTransport.getUserByQuery(query);


		comment.setUserId(userList.get(0).getId());
		comment.setIsHavingImg(itripAddCommentVO.getIsHavingImg());
		comment.setPositionScore(itripAddCommentVO.getPositionScore());
		comment.setFacilitiesScore(itripAddCommentVO.getFacilitiesScore());
		comment.setServiceScore(itripAddCommentVO.getServiceScore());
		comment.setHygieneScore(itripAddCommentVO.getHygieneScore());
		//综合评分
		int count = (itripAddCommentVO.getFacilitiesScore() + itripAddCommentVO.getServiceScore() + itripAddCommentVO.getPositionScore() + itripAddCommentVO.getHygieneScore())/4;
		comment.setScore(count);
		comment.setTripMode(itripAddCommentVO.getTripMode());
		comment.setIsOk(itripAddCommentVO.getIsOk());

		//进行添加评论
		int insertCount = hotelCommentTransport.addHotelComment(comment);
		if(insertCount > 0){
			return ResponseDto.success("评论成功");
		}
		return ResponseDto.failure("评论失败");
	}
}
