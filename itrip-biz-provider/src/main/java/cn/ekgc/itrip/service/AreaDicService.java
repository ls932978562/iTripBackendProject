package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.AreaDic;

import java.util.List;

/**
 *<b>爱旅行-主业务模块生产者业务层区域字典接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AreaDicService {

	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 */
	List<AreaDic> queryHotCity(AreaDic query)throws Exception;
}
