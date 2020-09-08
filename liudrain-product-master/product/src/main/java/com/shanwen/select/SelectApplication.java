package com.shanwen.select;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.shanwen.select.mapper")
public class SelectApplication {

    public static void main(String[] args) {

        SpringApplication.run(SelectApplication.class, args);
    }

}
