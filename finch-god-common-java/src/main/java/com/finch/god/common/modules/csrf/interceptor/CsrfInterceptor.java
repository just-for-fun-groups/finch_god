package com.finch.god.common.modules.csrf.interceptor;

import com.finch.god.common.modules.csrf.annotation.CsrfCheck;
import com.finch.god.common.modules.csrf.exception.CsrfException;
import com.finch.god.common.modules.csrf.service.impl.CheckContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class CsrfInterceptor extends HandlerInterceptorAdapter {

    private CheckContext checkContext;

    public CsrfInterceptor(CheckContext checkContext) {
        this.checkContext = checkContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod hm = null;
        if (handler instanceof  HandlerMethod) {
            hm = (HandlerMethod)handler;
        } else {
            return super.preHandle(request, response, handler);
        }

        Method method = hm.getMethod();
        if (!method.isAnnotationPresent(CsrfCheck.class)) {
            return super.preHandle(request, response, handler);
        }

        if (!checkContext.check(request, response)) {
            throw new CsrfException("invalid req");
        }

        return super.preHandle(request, response, handler);
    }

}
