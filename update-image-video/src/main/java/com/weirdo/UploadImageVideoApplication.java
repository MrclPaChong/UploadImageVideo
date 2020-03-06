package com.weirdo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.weirdo.dao")
public class UploadImageVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadImageVideoApplication.class, args);
    }

}
