package com;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageHelper;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 此项目使用的技术：swagger、jwt、切面日志、Shiro、code generator、druid
 * 
* <p>Title: iMaoApplication.java</p> 
* <p>Description: </p>
* @author chenkang
* @date 2018年10月31日  
* @version 1.0
 */
@SpringBootApplication
@EnableSwagger2
@MapperScan(value={"com.imao.*.mapper"})
public class iMaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(iMaoApplication.class, args);
	}
	
	//配置mybatis的分页插件pageHelper
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","mysql");    //配置postgresql数据库的方言支持Oracle,Mysql,MariaDB,SQLite,Hsqldb,PostgreSQL六种数据库
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}