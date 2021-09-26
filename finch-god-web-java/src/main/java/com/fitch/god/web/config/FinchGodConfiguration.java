package com.fitch.god.web.config;

import com.finch.god.common.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration("FinchGodConfiguration")
@EnableConfigurationProperties({FinchGodProperties.class})
public class FinchGodConfiguration extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(FinchGodConfiguration.class);

    @Resource
    private FinchGodProperties finchGodProperties;


    //登录拦截，先不用
//    @Profile({"live", "trunk", "pro" })
//    @Bean
//    public FilterRegistrationBean getLoginFilter() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new LoginFilter());
//        registration.addUrlPatterns("/*");
//        registration.addInitParameter("encoding", "UTF-8");
//        registration.addInitParameter("forceEncoding", "true");
//        registration.setName("LoginFilter");
//        registration.setOrder(Integer.MAX_VALUE - 999);
//        return registration;
//    }

    @PostConstruct
    protected void initialize() {
        logger.info("==========dyIcsProperties:" + finchGodProperties.getStaticUri());

        Constants.APP_CODE = finchGodProperties.getAppCode();

    }

}
