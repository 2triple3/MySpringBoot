package com.springboot.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;


@SpringBootApplication
@EnableDubbo
public class SpringBootUserApplication extends SpringBootServletInitializer {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUserApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootUserApplication.class);
	}
}
