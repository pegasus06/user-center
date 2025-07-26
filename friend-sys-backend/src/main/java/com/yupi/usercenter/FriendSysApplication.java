package com.yupi.usercenter;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.yupi.usercenter.mapper")
public class FriendSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendSysApplication.class, args);
    }

}

