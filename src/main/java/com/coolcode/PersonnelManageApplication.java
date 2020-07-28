package com.coolcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching      //开启redis缓存
@MapperScan("com.coolcode.dao")
public class PersonnelManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonnelManageApplication.class, args);
    }

}
