package com.finch.god.common.modules.csrf.config;

import com.finch.god.common.modules.csrf.interceptor.CsrfInterceptor;
import com.finch.god.common.modules.csrf.service.impl.CheckContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Resource
    private CheckContext checkContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CsrfInterceptor(this.checkContext)).excludePathPatterns("/error");
    }

}
