package cn.ekgc.itrip.dao;


import cn.ekgc.itrip.pojo.entity.AreaDic;

import java.util.List;

/**
 *<b>爱旅行-主业务模块生产者数据持久层区域字典接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public interface AreaDicDao {
	/**
	 * <b>查询区域信息</b>
	 * @param areaDic
	 * @return
	 * @throws Exception
	 */
	List<AreaDic> findAreaDicByQuery(AreaDic areaDic)throws Exception;
}
