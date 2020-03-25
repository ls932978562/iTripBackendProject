package cn.ekgc.itrip.util;

import cn.ekgc.itrip.pojo.entity.Hotel;
import sun.rmi.runtime.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * <b>爱旅行-生成酒店订单编号</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class HotelOrderNoCreater {

	/**
	 * <b>根据酒店Id，房间Id，当前时间，随机数生成订单编号</b>
	 * @param roomId
	 * @param hotelId
	 * @return
	 */
	public static String createOrderNo(Long roomId, Long hotelId){
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		//增加酒店id
		sb.append(hotelId);
		//增加房间Id
		sb.append(roomId);
		//增加当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSSSS");
		String time= dateFormat.format(new Date());
		sb.append(time);
		//增加随机数
		sb.append(random.nextInt(10));
		//对生成的结果进行MD5加密并变成大写
		return MD5Util.encrypt(sb.toString()).toUpperCase();
	}

}
