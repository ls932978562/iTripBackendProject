package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.ItripImgDao;
import cn.ekgc.itrip.pojo.entity.ItripImage;
import cn.ekgc.itrip.service.ItripImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>爱旅行-酒店图片信息业务层接口实现类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("itripImgService")
@Transactional
public class ItripImgServiceImpl implements ItripImgService {

	@Autowired
	private ItripImgDao itripImgDao;
	/**
	 * <b>根据targetId查询酒店房型图片(type=0)</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<ItripImage> getHotelImg(ItripImage query) throws Exception {
		List<ItripImage> itripImageList = itripImgDao.findImgListByQuery(query);

		if(itripImageList != null){
			return itripImageList;
		}
		return new ArrayList<ItripImage>();
	}



	/**
	 * <b>保存图片</b>
	 * @param itripImage
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/save")
	public void saveImage(ItripImage itripImage) throws Exception {
		itripImgDao.saveImage(itripImage);
	}
}
