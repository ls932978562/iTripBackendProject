package cn.ekgc.itrip;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration     //提示spring 该类是配置类
@EnableSwagger2     //swagger可以使用的配置类
public class SwaggerConfiguration {

	@Bean
	public Docket crateDocket(){
		//创建docket对象
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		//设定swagger在生成UI界面时所显示的信息
		docket.apiInfo(apiInFo());
		return docket;
	}

	//创建swagger文档页面相关信息的对象
	private ApiInfo apiInFo(){
		ApiInfo apiInfo = new ApiInfoBuilder()
				.title("爱旅行项目文档交互接口")
				.version("1.0.0")
				.contact(new Contact("张三", "", "96523@163.com"))
				.build();
		return apiInfo;
	}
}
