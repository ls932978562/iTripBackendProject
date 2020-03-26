package cn.ekgc.itrip.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * <b>爱旅行-邮件发送工具类</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
@Component("mainSenderUtil")
public class MailSenderUtil {

	@Autowired
	private JavaMailSender mailSender;

	/**
	 * <b>通过注册邮箱地址发送邮箱注册激活码</b>
	 * @param email
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public boolean sendActivatedCodeMail(String email,String activeCode)throws Exception{
		try {
			//发送具有html样式的邮件
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
			//设置发件人邮箱地址
			messageHelper.setFrom("luoshuaiwz@163.com");
			//设置抄送人地址
			messageHelper.setCc("luoshuaiwz@163.com");
			//设置收件人地址
			messageHelper.setTo(email);
			//设置邮件主题
			messageHelper.setSubject("爱旅行-用户注册邮件激活码");
			//邮件的html文件字符串
			String content = "<div>亲爱的&nbsp;" + email + "&nbsp;你好:<br>" +
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>" +
					"感谢您注册为爱旅行会员！</div><div><br>以下是您的会员激活码：" +
					"<span style='font-size: 24px;'><b>" + activeCode + "</b></span></div>" +
					"<div>请在<span style='font-size: 24px;'><b>30分钟</b></span>内登录网站进行激活！！！</div>" +
					"<div>&nbsp; &nbsp; &nbsp;&nbsp;</div>";
			//设置邮件发送内容
			messageHelper.setText(content, true);
			//发送邮件
			mailSender.send(mimeMessage);
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}


	/**
	 * <b>发送普通文本邮件</b>
	 * @param email
	 * @param activeCode
	 * @return
	 * @throws Exception
	 */
	public  boolean sendNomalTextEmail(String email,String activeCode)throws Exception{
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom("luoshuaiwz@163.com");
			mailMessage.setCc("luoshuaiwz@163.com");
			mailMessage.setTo(email);
			mailMessage.setSubject("爱旅行-用户注册邮件激活码");
			mailMessage.setText("欢迎" + email + "注册爱旅行用户系统，请提取用户激活码" + activeCode +"进行账户激活。此激活码30分钟内有效！！！");
			mailSender.send(mailMessage);
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
