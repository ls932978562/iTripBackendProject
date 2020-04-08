package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.dao.HotelCommentDao;
import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.entity.HotelComment;
import cn.ekgc.itrip.pojo.vo.ListCommentVO;
import cn.ekgc.itrip.pojo.vo.ScoreCommentVO;
import cn.ekgc.itrip.pojo.vo.SearchCommentVO;
import cn.ekgc.itrip.service.HotelCommentService;
import cn.ekgc.itrip.util.constant.SystemConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * <b>爱旅行-酒店评论业务层接口实现类</b>、
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("hotelCommentService")
@Transactional
public class HotelCommentServiceImpl implements HotelCommentService {

	@Autowired
	private HotelCommentDao hotelCommentDao;

	/**
	 * <b>据酒店id查询酒店平均分</b>
	 * @param hotelComment
	 * @return
	 * @throws Exception
	 */
	public ScoreCommentVO getHotelScore(HotelComment hotelComment) throws Exception {
		ScoreCommentVO scoreCommentVO = hotelCommentDao.queryHotelScore(hotelComment);

		//将查询到的Float数据保留一位小数
		DecimalFormat fnum = new DecimalFormat("##0.0");
		scoreCommentVO.setAvgFacilitiesScore(Float.parseFloat(fnum.format(scoreCommentVO.getAvgFacilitiesScore())));
		scoreCommentVO.setAvgHygieneScore(Float.parseFloat(fnum.format(scoreCommentVO.getAvgHygieneScore())));
		scoreCommentVO.setAvgPositionScore(Float.parseFloat(fnum.format(scoreCommentVO.getAvgPositionScore())));
		scoreCommentVO.setAvgServiceScore(Float.parseFloat(fnum.format(scoreCommentVO.getAvgServiceScore())));
		scoreCommentVO.setAvgScore(Float.parseFloat(fnum.format(scoreCommentVO.getAvgScore())));

		return scoreCommentVO;
	}

	/**
	 * <b>根据评论类型查询评论列表，并分页显示</b>
	 * @param searchCommentVO
	 * @return
	 * @throws Exception
	 */
	public Page<ListCommentVO> getcommentlist(SearchCommentVO searchCommentVO) throws Exception {
		Page<ListCommentVO> page = new Page<ListCommentVO>();
		//封装查询参数
		HotelComment hotelComment = new HotelComment();
		hotelComment.setHotelId(searchCommentVO.getHotelId());
		hotelComment.setIsHavingImg(searchCommentVO.getIsHavingImg());
		hotelComment.setIsOk(searchCommentVO.getIsOk());

		//设置分页信息


		PageHelper.startPage(searchCommentVO.getPageNo(), searchCommentVO.getPageSize());
		//直接进行查询
		List<ListCommentVO> hotelCommentList = hotelCommentDao.queryHotelCommentList(hotelComment);
		//使用pageInfo对结果进行封装
		PageInfo<ListCommentVO> pageInfo = new PageInfo<ListCommentVO>(hotelCommentList);

		page.setRows(hotelCommentList);
		page.setTotal((int)pageInfo.getTotal());
		//总页数
		page.setPageCount(pageInfo.getPages());

		return page;
	}

	/**
	 * <b>根据酒店id查询各类评论数量</b>
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Integer getCommentCounts(Map<String, Object> map) throws Exception {
		Integer counts = hotelCommentDao.findCommentCounts(map);
		if(counts != null){
			return counts;
		}
		return 0;
	}



	/**
	 * <b>新增评论</b>
	 * @param comment
	 * @return
	 * @throws Exception
	 */
	public int addHotelComment(HotelComment comment) throws Exception {
		return hotelCommentDao.saveHotelComment(comment);
	}



	/**
	 *
	 * <b>查询订单</b>
	 * @param hotelComment
	 * @return
	 * @throws Exception
	 */
	public List<HotelComment> getHotelComment(HotelComment hotelComment) throws Exception {
		return hotelCommentDao.findHotelComment(hotelComment);
	}


}
