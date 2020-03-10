package cn.ekgc.itrip.util;

/**
 * <b>爱旅行-格式校验工具类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class CheckFormatUtil {
	//设置邮箱格式
	public static final String emailFormat="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	// 设置手机号码正则表达式
	private static final String cellphoneFormat = "1\\d{10}";
	/**
	 * <b>校验邮箱格式</b>
	 * @param name
	 * @return
	 */
	public static boolean checkEmailFormat(String name){
		if(name != null && !"".equals(name.trim())){
			boolean flag = name.matches(emailFormat);
			if(flag){
				return true;
			}
		}
		return false;
	}

	public static boolean checkPhoneFormat(String cellphone){
		if(cellphone != null && !"".equals(cellphone.trim())){
			boolean flag = cellphone.matches(cellphoneFormat);
			if(flag){
				return true;
			}
		}
		return false;
	}
}
