package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.AreaDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/**
 * <b>爱旅行-区域字典信息传输层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/area/trans")
public interface AreaDicTransport {

	/**
	 * <b>查询热门城市</b>
	 * @param query
	 * @return
	 */
	@PostMapping("/query")
	List<AreaDic> queryHotCity(@RequestBody AreaDic query)throws Exception;
}
