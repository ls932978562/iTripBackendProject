package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.entity.HotelComment;
import cn.ekgc.itrip.pojo.vo.ListCommentVO;
import cn.ekgc.itrip.pojo.vo.ScoreCommentVO;
import cn.ekgc.itrip.pojo.vo.SearchCommentVO;
import cn.ekgc.itrip.service.HotelCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店评论传输层接口实现类</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("/hotelCommentTransport")
@RequestMapping("/hotel/trans")
public class HotelCommentTransportImpl implements HotelCommentTransport {

	@Autowired
	private HotelCommentService hotelCommentService;

	/**
	 * <b>据酒店id查询酒店平均分</b>
	 * @param hotelComment
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/comment")
	public ScoreCommentVO getHotelScore(@RequestBody HotelComment hotelComment) throws Exception {
		return hotelCommentService.getHotelScore(hotelComment);
	}

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param searchCommentVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/commentlist")
	public Page<HotelComment> getcommentlist(@RequestBody SearchCommentVO searchCommentVO) throws Exception {
		return hotelCommentService.getcommentlist(searchCommentVO);
	}


	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/count")
	public Integer getCommentCounts(@RequestBody Map<String, Object> map) throws Exception {
		return hotelCommentService.getCommentCounts(map);
	}


	/**
	 * <b>新增评论</b>
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addComment")
	public int addHotelComment(@RequestBody HotelComment comment) throws Exception {
		return hotelCommentService.addHotelComment(comment);
	}


}
