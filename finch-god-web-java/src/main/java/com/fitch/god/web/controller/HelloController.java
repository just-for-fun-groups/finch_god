package com.fitch.god.web.controller;

import com.finch.god.common.config.Constants;
import com.finch.god.common.exception.ExceptionCast;
import com.finch.god.common.vo.ResultInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return Constants.getAppCode();
    }

    /**
     * 全局异常示例
     */
    @RequestMapping("/exception")
    public ResultInfo exception() {
        //手动抛出业务异常，直接返回业务提示，代码不会往下执行
        ExceptionCast.cast("异常测试");
        //实际放回结果{"code":-1,"msg":"异常测试","data":null}
        return ResultInfo.success("111");
    }

}
