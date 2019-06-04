 package com.gym.wechat;

import io.swagger.annotations.ApiOperation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目名称：apidoc
 * 
 * @description:
 * @author Wind-spg
 * @create_time：2015年2月10日 上午10:27:51
 * @version V1.0.0
 * 
 */

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = { "com.bitdobo" })
public class SwaggerConfigation {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("接口文档 ").description("更多文档请关注公众号")
				.termsOfServiceUrl("http://www.baidu.com").contact("test")
				.version("1.0").build();
	}

}