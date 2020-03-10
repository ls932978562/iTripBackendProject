package cn.ekgc.itrip.util.constant;

import java.util.Properties;

/**
 * <b>爱旅行-短信息发送常量工具类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class SmsConstant {

	private static final Properties props = new Properties();

	static{
		//加载properties属性文件
		try {
			props.load(SmsConstant.class.getClassLoader().getResourceAsStream("props/sms.properties"));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static final String SMS_ADDRESS = props.getProperty("sms.address");
	public static final String SMS_PORT = props.getProperty("sms.port");
	public static final String SMS_ACCOUNTSID = props.getProperty("sms.accountsid");
	public static final String SMS_ACCOUNTTOKEN = props.getProperty("sms.accounttoken");
	public static final String SMS_APPID = props.getProperty("sms.appid");
	public static final String SMS_TEMPID = props.getProperty("sms.tempid");
}
