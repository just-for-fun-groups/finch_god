package com.fitch.god.web;

import com.finch.god.common.CommonApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@MapperScan(basePackages = "com.finch.god.common.dao")
@SpringBootApplication(scanBasePackages ={"com.fitch.god.web","com.finch.god.common"})
@ComponentScan(basePackageClasses = {WebApplication.class, CommonApplication.class})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
