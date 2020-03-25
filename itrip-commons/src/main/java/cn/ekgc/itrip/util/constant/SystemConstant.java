package cn.ekgc.itrip.util.constant;

import java.util.Properties;

/**
 * <b>读取token密钥工具类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class SystemConstant {
	private static Properties props = new Properties();

	static {
		try {
			//加载properties文件路径
			props.load(SystemConstant.class.getClassLoader().getResourceAsStream("props/commons.properties"));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static final String SECRET_KEY = props.getProperty("secret.key");

}
