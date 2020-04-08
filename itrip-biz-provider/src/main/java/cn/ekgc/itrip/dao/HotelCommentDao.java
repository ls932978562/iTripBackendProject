package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.HotelComment;
import cn.ekgc.itrip.pojo.vo.ListCommentVO;
import cn.ekgc.itrip.pojo.vo.ScoreCommentVO;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店评论数据持久层接口</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface HotelCommentDao {

	/**
	 * <b>据酒店id查询酒店平均分</b>
	 * @param hotelComment
	 * @return
	 * @throws Exception
	 */
	ScoreCommentVO queryHotelScore(HotelComment hotelComment)throws Exception;


	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param hotelComment
	 * @return
	 * @throws Exception
	 */
	List<ListCommentVO> queryHotelCommentList(HotelComment hotelComment)throws Exception;


	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	Integer findCommentCounts(Map<String, Object> map)throws Exception;



	/**
	 * <b>新增评论</b>
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	int saveHotelComment(HotelComment comment)throws Exception;


	/**
	 *
	 * <b>查询订单</b>
	 * @param hotelComment
	 * @return
	 * @throws Exception
	 */
	List<HotelComment> findHotelComment(HotelComment hotelComment)throws Exception;
}
