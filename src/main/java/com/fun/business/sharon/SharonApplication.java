package com.fun.business.sharon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 项目启动入口
 *
 */
@SpringBootApplication(scanBasePackages = { "com.fun.business.sharon" }, exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.fun.business.sharon.biz.*.dao")
public class SharonApplication {

	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);

	public static void main(String[] args) {
		SpringApplication.run(SharonApplication.class, args);
		fixedThreadPool.execute(() -> {
			for (int i = 0; i < 10; i++){
				System.out.println("当前值：" + i);
			}
		});
		System.out.println("sharon is running, please login on http:localhost:9080/sharon/swagger-ui.html");
	}
}
