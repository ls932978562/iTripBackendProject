package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.ItripImage;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * <b>爱旅行-酒店图片信息数据持久层接口</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface ItripImgDao {

	/**
	 * <b>根据targetId查询酒店房型图片(type=0)</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<ItripImage> findImgListByQuery(ItripImage query)throws Exception;


	/**
	 * <b>保存图片</b>
	 * @param itripImage
	 * @return
	 * @throws Exception
	 */
	void saveImage(ItripImage itripImage)throws Exception;
}
