package com.zhiyi.vestation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan(value = "com.zhiyi.vestation.mapper")
public class VestationApplication {

    
    public static void main(String[] args) {
        SpringApplication.run(VestationApplication.class, args);
    }

}
