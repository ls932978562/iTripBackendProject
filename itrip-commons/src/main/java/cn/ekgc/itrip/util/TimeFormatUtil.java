package cn.ekgc.itrip.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <b>格式化UTC时间格式工具类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class TimeFormatUtil {

	/**
	 * <b>格式化时间</b>
	 * @param dateUTC
	 * @return
	 * @throws Exception
	 */
	public static Date formatUTCTime(Date dateUTC)throws Exception{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf1.parse(sdf1.format(dateUTC));                     //拿到Date对象
		String str = sdf2.format(date);                                   //输出格式：2017-01-22 09:28:33
		Date formatDate = sdf2.parse(str);
		return formatDate;
	}
}
