package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.ItripImage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>爱旅行-酒店图片信息传输层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/img/trans")
public interface ItripImgTransport {

	/**
	 * <b>根据targetId查询酒店房型图片(type=0)</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/queryImg")
	List<ItripImage> getHotelImg(@RequestBody ItripImage query)throws Exception;
}
