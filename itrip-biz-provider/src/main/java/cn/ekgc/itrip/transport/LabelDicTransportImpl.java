package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.LabelDic;
import cn.ekgc.itrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-标签字典信息传输层接口实现类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("labelDicTransport")
@RequestMapping("/feature/trans")
public class LabelDicTransportImpl implements LabelDicTransport {

	@Autowired
	private LabelDicService labelDicService;
	/**
	 * <b>查询酒店特色</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	public List<LabelDic> getListByQuery(@RequestBody LabelDic labelDic) throws Exception {
		return labelDicService.getListByQuery(labelDic);
	}
}
