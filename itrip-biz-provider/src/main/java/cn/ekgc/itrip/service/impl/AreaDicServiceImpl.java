package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.AreaDicDao;
import cn.ekgc.itrip.pojo.entity.AreaDic;
import cn.ekgc.itrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *<b>爱旅行-主业务模块生产者业务层区域字典接口实现类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("areaDicService")
@Transactional
public class AreaDicServiceImpl implements AreaDicService {

	@Autowired
	private AreaDicDao areaDicDao;
	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 */
	public List<AreaDic> queryHotCity(AreaDic query) throws Exception{
		//查询热门城市列表
		List<AreaDic> areaDicList = areaDicDao.findAreaDicByQuery(query);
		if(areaDicList != null){
			return areaDicList;
		}
		return new ArrayList<AreaDic>();
	}
}
