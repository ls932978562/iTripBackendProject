package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.enums.OrderStatusEnum;
import cn.ekgc.itrip.base.pojo.vo.Page;
import cn.ekgc.itrip.base.pojo.vo.ResponseDto;
import cn.ekgc.itrip.dao.HotelDao;
import cn.ekgc.itrip.dao.HotelOrderDao;
import cn.ekgc.itrip.dao.HotelroomDao;
import cn.ekgc.itrip.pojo.entity.AreaDic;
import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.pojo.vo.*;
import cn.ekgc.itrip.service.HotelService;
import cn.ekgc.itrip.transport.AreaDicTransport;
import cn.ekgc.itrip.util.constant.SystemConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>爱旅行-酒店信息业务层接口实现类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelDao hotelDao;
	@Autowired
	private SolrClient solrClient;
	@Autowired
	private HotelroomDao hotelroomDao;
	@Autowired
	private HotelOrderDao hotelOrderDao;
	@Autowired
	private AreaDicTransport areaDicTransport;
	/**
	 * <b>查询热门城市酒店列表</b>
	 * @param queryVo
	 * @return
	 * @throws Exception
	 */
	public List<HotelVo> searchHotelListByHotCity(HotCityVo queryVo) throws Exception {
		// 对于Spring Boot注入的SolrClient就是HttpSolrClient对象，进行强制类型转换
		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		httpSolrClient.setParser(new XMLResponseParser());
		// 创建Solr的查询参数
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("cityId:" + queryVo.getCityId());
		solrQuery.setRows(queryVo.getCount());
		// 使用SolrClient进行查询，QueryResponse
		QueryResponse queryResponse = solrClient.query(solrQuery);
		// 通过使用QueryResponse提取结果
		return queryResponse.getBeans(HotelVo.class);


	}

	/**
	 * <b>查询酒店特色、商圈、酒店名称</b>
	 * @return
	 * @throws Exception
	 */
	public Hotel getvideodesc(Long hotelId) throws Exception {
		//封装查询对象
		Hotel hotel = new Hotel();
		hotel.setId(hotelId);
		//进行查询
		List<Hotel> hotelList = hotelDao.findHotelListByQuery(hotel);
		//查询结果只可能有一个
		if(hotelList != null && hotelList.size() > 0){
			return hotelList.get(0);
		}
		return new Hotel();
	}



	/**
	 * <b>查询酒店分页</b>
	 * @param searchHotelVO
	 * @return
	 * @throws Exception
	 */
	public Page<HotelVo> searchHotelListByQuery(SearchHotelVO searchHotelVO) throws Exception {
		Page<HotelVo> page = new Page<HotelVo>();
		if (searchHotelVO.getPageNo() == null) {
			searchHotelVO.setPageNo(1);
		}
		if(searchHotelVO.getPageSize() == null){
			searchHotelVO.setPageSize(5);
		}

		// 对于Spring Boot注入的SolrClient就是HttpSolrClient对象，进行强制类型转换
		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		httpSolrClient.setParser(new XMLResponseParser());
		// 创建Solr的查询参数
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("destination:" + searchHotelVO.getDestination());
		solrQuery.setQuery("hotelLevel:" + searchHotelVO.getHotelLevel());

		solrQuery.setStart((searchHotelVO.getPageNo() - 1) * searchHotelVO.getPageSize());
		solrQuery.setRows(searchHotelVO.getPageSize());

		// 使用SolrClient进行查询，QueryResponse
		QueryResponse queryResponse = solrClient.query(solrQuery);
		SolrDocumentList docs = queryResponse.getResults();
		page.setPageSize(solrQuery.getRows());
		page.setCurPage(searchHotelVO.getPageNo());
		page.setTotal(new Long(docs.getNumFound()).intValue());
		page.setPageSize(searchHotelVO.getPageSize());
		// 通过使用QueryResponse提取结果
		List<HotelVo> hotelVoList =  queryResponse.getBeans(HotelVo.class);
		page.setRows(hotelVoList);

		return page;
	}
}
