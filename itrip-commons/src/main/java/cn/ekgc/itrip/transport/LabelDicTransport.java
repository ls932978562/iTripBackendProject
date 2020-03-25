package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.LabelDic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>爱旅行-标签字典信息传输层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(value = "itrip-biz-provider")
@RequestMapping("/feature/trans")
public interface LabelDicTransport {

	/**
	 * <b>查询酒店特色</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/query")
	List<LabelDic> getListByQuery(@RequestBody LabelDic labelDic)throws Exception;
}
