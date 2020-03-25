package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.controller.BaseController;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;

@RestController("/payDemo")
public class PayDemo extends BaseController {

	@GetMapping(value = "/trade")
	public void textTrade()throws Exception{
		AlipayClient alipayClient =  new DefaultAlipayClient(
				"https://openapi.alipay.com/gateway.do" ,
				"2016101800714676",
				"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCxwix/lcynTgScSi/h348y7FOOnbZyB/xOz5jzeD5nlr5F9pNyoR1gpjrAD6NhSpbZFG8ytIJLKnyXPPfOzirYvZEGjN4GbRuwH6mXgdpDMtJFzoqHFSw3+uKcHMG/8rJiKEiroGOXfXzK2QtWoTqJWYjfZ5tYH7S+n2wfDqtm61JzLZZWcd64Z7bI6JhDC36AUtbEG4nLQXEnCytiPDm5EIoncc+8/5VCjqFZeg6qgEnbELGz2mzNYqpVU7Pshw42xB/re7JtRsgbqBJUsFVPwK9WNGODJfRvXMWjbec3cwnfElFT5fPlfkt8/vWQ6D54Zxx2VQtAAonWBqfuFiQBAgMBAAECggEAFmEm7w0yDeJBrFeevaZCxhuAzZTHkoW020tBq215FduhqvdHSwhDxZZIEUQmwqn8YeozscOOH96Cfxv66vuD43PCXXvAvDhiYwMpe9yqWQl99bHyON2OiqG4hAUO05a/pKIDvC/eOgVff75hsg9sajxuggnuQeMEA3fYyfQz2ioSqORJn57yO4xj/uW9vkWw6fW7y7Pj+OoEVbUFP0dZmhMXIxwu+3rBJGdxhH/inGsEG2CsTHIiOJSvLmbtpdM+SNJSnvCW5WsSfOkSdyaU58OcGKM0jytbjg7vWIqRoIxHZ+fxE7aUe6YdoXMKkN0lOtgQbrFow/twk07J+w/xQQKBgQDXLJvls0xOFv31I3pzTq0nOia9CKRNomlp8FEMBGz6VEW3iiuOXlX0TMZzqSudRMcAPnXU3t/wtLIsGRPzO5NMuFzLJJRLCWEpwDBC07UOBc0kSpy6/3xsJjfB2RZcRaVOc+P5qnEeNMkXzt81o2sDMyzog4vs/wP73BE1qsNC2QKBgQDTfDczWt5FQG+Skew3Ykg2lT0kRIz+o9VaKAJqcP8YIcjUmAVgQGt+i/aYu2bDq5tseS3wQDlK9ZIAW6T9oo8qFQy4TU3rr+m3jSe4KjrZoV4/bQmU5NcGnOargU7hkSisHeoFwzgUI7xUTXSbWbAM0vKN1v2hw2R9kjnJ8tfhaQKBgQCblwEfpnfNm6iFQuM/T6u7SrxKQl50WLU6ynAAbY7TsG49GmcGakZjAcUaS0BjVyFCluejRonNxwtzAatAXVrUxhcrOUcVvzGH/BWprOYlvqIo1N7iBO3WUghNXolhrWasBPbbSJjFC8xBL/CNG5680JtvgfeDJp+d1fKHyPOqeQKBgQDOC2iQnUiO+g96Irec4iFvJIm0SyF/ekmElBR+debjMQ9EqXQbacgRO/Yeqf6eRxSfY5TxTPFE6eq8dI1UG8wV+PoymgZ/yR0RGvKGyIYbwM9dRjOeoDIrHDTVBZ+/vrh8sIxv3tpFLOfwzqH8NS6D4AY3US49K2w9t3icHABeeQKBgHInkamOsI5a7WwlIWZJq7ycTVcX1vMXHQ24iZ1/Su/Zcui80PLREaIqdSrIXEHrCzbbz2owvQE6aYGJpKGc2eKa5uR6kzCbfX0p4ULcn5HfuHsZslbMHEo4osWMXiYBqfyBVdxbr48XaKZ0l8Qs/Uskkro0WjhbQkEJaD1DBbkH",
				"json",
				"UTF-8",
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAscIsf5XMp04EnEov4d+PMuxTjp22cgf8Ts+Y83g+Z5a+RfaTcqEdYKY6wA+jYUqW2RRvMrSCSyp8lzz3zs4q2L2RBozeBm0bsB+pl4HaQzLSRc6KhxUsN/rinBzBv/KyYihIq6Bjl318ytkLVqE6iVmI32ebWB+0vp9sHw6rZutScy2WVnHeuGe2yOiYQwt+gFLWxBuJy0FxJwsrYjw5uRCKJ3HPvP+VQo6hWXoOqoBJ2xCxs9pszWKqVVOz7IcONsQf63uybUbIG6gSVLBVT8CvVjRjgyX0b1zFo23nN3MJ3xJRU+Xz5X5LfP71kOg+eGccdlULQAKJ1gan7hYkAQIDAQAB",
				"RSA2");
		//获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
		alipayRequest.setReturnUrl( "http://domain.com/CallBack/return_url.jsp" );
		alipayRequest.setNotifyUrl( "http://localhost/itrip" ); //在公共参数中设置回跳和通知地址
		alipayRequest.setBizContent( "{"  +
				"    \"out_trade_no\":\"20150320010101001\","  +
				"    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
				"    \"total_amount\":88.88,"  +
				"    \"subject\":\"Iphone6 16G\","  +
				"    \"body\":\"Iphone6 16G\","  +
				"    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
				"    \"extend_params\":{"  +
				"    \"sys_service_provider_id\":\"2088511833207846\""  +
				"    }" +
				"  }" ); //填充业务参数
		String form= "" ;
		try  {
			form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
		}  catch  (AlipayApiException e) {
			e.printStackTrace();
		}
		response.setContentType( "text/html;charset=utf-8");
		response.getWriter().write(form); //直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();
	}
}
