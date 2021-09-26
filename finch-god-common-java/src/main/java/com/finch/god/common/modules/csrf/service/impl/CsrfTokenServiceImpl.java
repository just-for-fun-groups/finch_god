package com.finch.god.common.modules.csrf.service.impl;


import com.finch.god.common.modules.csrf.config.CsrfConfiguration;
import com.finch.god.common.modules.csrf.service.CsrfTokenService;
import com.finch.god.common.modules.csrf.utils.TokenUtils;
import com.finch.god.common.utils.CookieUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CsrfTokenServiceImpl implements CsrfTokenService {

    @Resource
    private CsrfConfiguration csrfConfiguration;

    @Override
    public String getToken(HttpServletRequest request) {
        return CookieUtils.getValue(request, csrfConfiguration.tokenName());
    }

    @Override
    public void setToken(HttpServletResponse response) {
        CookieUtils.setCookie(response, csrfConfiguration.tokenName(), TokenUtils.generate(), csrfConfiguration.tokenMaxAge());
    }

    @Override
    public boolean isValid(String token) {
        return TokenUtils.isValid(token);
    }


}
