package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.AreaDic;
import cn.ekgc.itrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *<b>爱旅行-主业务模块生产者传输层区域字典接口实现类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("areaDicTransportImpl")
@RequestMapping("/area/trans")
public class AreaDicTransportImpl implements AreaDicTransport{

	@Autowired
	private AreaDicService areaDicService;
	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 */
	@PostMapping("/query")
	public List<AreaDic> queryHotCity(@RequestBody AreaDic query)throws Exception {
		return areaDicService.queryHotCity(query);
	}
}
