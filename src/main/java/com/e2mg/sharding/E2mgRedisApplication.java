package com.e2mg.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.e2mg.sharding.mapper")
public class E2mgRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(E2mgRedisApplication.class, args);
    }

}
