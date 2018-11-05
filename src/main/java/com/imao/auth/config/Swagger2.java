package com.imao.auth.config;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
/**
 * 
* <p>Title: Swagger2.java</p> 
* <p>Description: Swagger配置类</p>
* @author liuy
* @date 2018年8月8日  
* @version 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	
	@Value("${swagger2.enable}")
	private boolean swagger2Enable;
	
	/**
	 * 权限管理API文档
	 */
	@Bean
    public Docket sys_api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.imao.auth"))
                .paths(PathSelectors.any()).build().groupName("权限管理接口文档V1.0").enable(swagger2Enable)
                .apiInfo(apiInfo("权限管理接口文档V1.0","文档中可以查询及测试接口调用参数和结果","1.0"));
    }
	
	/**
	 * 附件API文档
	 */
    @Bean
    public Docket netcar_api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.imao.attachment"))
                .paths(PathSelectors.any()).build().groupName("附件接口文档V1.0").enable(swagger2Enable)
                .apiInfo(apiInfo("附件API接口文档V1.0","文档中可以查询及测试接口调用参数和结果","1.0"));
    }
    
    
    private ApiInfo apiInfo(String name,String description,String version) {
        ApiInfo apiInfo = new ApiInfoBuilder().title(name).description(description).version(version).build();
        return apiInfo;
    }
}
