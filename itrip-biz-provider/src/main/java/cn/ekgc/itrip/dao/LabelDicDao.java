package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.LabelDic;

import java.util.List;

/**
 * <b>爱旅行-标签字典信息数据持久层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public interface LabelDicDao {

	/**
	 * <b>查询酒店特色</b>
	 * @return
	 * @throws Exception
	 */
	List<LabelDic> findLabelDicByQuery(LabelDic labelDic)throws Exception;
}
