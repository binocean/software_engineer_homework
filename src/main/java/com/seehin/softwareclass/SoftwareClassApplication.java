package com.seehin.softwareclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
* @Author seehin
* @Date 2020/5/1
**/
@SpringBootApplication
@EnableScheduling
@MapperScan(value = "com.seehin.softwareclass.mapper")
public class SoftwareClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoftwareClassApplication.class, args);
    }

}
