package com.goktech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.goktech.mapper")
public class ExcelApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class, args);
    }
    
}
