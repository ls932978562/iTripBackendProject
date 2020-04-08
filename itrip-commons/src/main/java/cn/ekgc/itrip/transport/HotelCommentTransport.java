package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.pojo.entity.HotelComment;
import cn.ekgc.itrip.pojo.entity.HotelOrder;
import cn.ekgc.itrip.pojo.vo.ListCommentVO;
import cn.ekgc.itrip.pojo.vo.ScoreCommentVO;
import cn.ekgc.itrip.pojo.vo.SearchCommentVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店评论传输层接口</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(value = "itrip-biz-provider")
@RequestMapping("/hotel/trans")
public interface HotelCommentTransport {

	/**
	 * <b>据酒店id查询酒店平均分</b>
	 * @param hotelComment
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/comment")
	ScoreCommentVO getHotelScore(@RequestBody HotelComment hotelComment)throws Exception;

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param searchCommentVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/commentlist")
	Page<ListCommentVO> getcommentlist(@RequestBody SearchCommentVO searchCommentVO)throws Exception;

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/count")
	Integer getCommentCounts(@RequestBody Map<String, Object> map)throws Exception;


	/**
	 * <b>新增评论</b>
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/addComment")
	int addHotelComment(@RequestBody HotelComment comment)throws Exception;

	/**
	 *
	 * <b>查询订单</b>
	 * @param hotelComment
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/getcommentlist")
	List<HotelComment> getHotelComment(@RequestBody HotelComment hotelComment)throws Exception;
}
