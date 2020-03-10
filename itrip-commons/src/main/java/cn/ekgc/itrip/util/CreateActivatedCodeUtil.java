package cn.ekgc.itrip.util;

import java.util.Random;

/**
 * <b>爱旅行-生成验证激活码</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class CreateActivatedCodeUtil {
	public static String createActivatedCode(){
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int i = 0; i < 6; i++){
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}
}
