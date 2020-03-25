package cn.ekgc.itrip.transport;

import cn.ekgc.itrip.pojo.entity.ItripImage;
import cn.ekgc.itrip.service.ItripImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>爱旅行-酒店图片信息传输层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("itripImgTransport")
@RequestMapping("/img/trans")
public class ItripImgTransportImpl implements ItripImgTransport{

	@Autowired
	private ItripImgService itripImgService;
	/**
	 * <b>根据targetId查询酒店房型图片(type=0)</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/queryImg")
	public List<ItripImage> getHotelImg(@RequestBody ItripImage query) throws Exception {
		return itripImgService.getHotelImg(query);
	}
}
