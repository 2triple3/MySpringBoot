package com.springboot.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;


@SpringBootApplication
@EnableDubbo
public class SpringBootSystemApplication extends SpringBootServletInitializer {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSystemApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootSystemApplication.class);
	}
}
