package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import com.example.demo.config.DataSourceConfig;

@SpringBootApplication
@EnableWebMvc//开启springmvc
//@Import({Swagger2Config.class, DataSourceConfig.class})//导入配置类;可显示声明，也可以通过保证放在扫描包目录
@ServletComponentScan //为了扫描Servlet
@ImportResource(locations={"classpath:applicationContext.xml"})//导入配置xml;不能使用默认的根目录下的application.xml，会冲突
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
