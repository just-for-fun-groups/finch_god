package com.fitch.god.web.controller;

import com.fitch.god.web.config.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return Constants.getAppCode();
    }

}
