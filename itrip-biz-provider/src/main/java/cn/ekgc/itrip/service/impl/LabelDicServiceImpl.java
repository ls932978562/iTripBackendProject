package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.LabelDicDao;
import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-标签字典信息业务层接口实现类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("labelDicService")
@Transactional
public class LabelDicServiceImpl implements LabelDicService {

	@Autowired
	private LabelDicDao labelDicDao;

	/**
	 * <b>查询酒店特色</b>
	 * @return
	 * @throws Exception
	 */
	public List<LabelDic> getListByQuery(LabelDic labelDic) throws Exception {
		List<LabelDic> labelDicList = labelDicDao.findLabelDicByQuery(labelDic);
		if(labelDicList != null){
			return labelDicList;
		}
		return new ArrayList<LabelDic>();
	}
}
