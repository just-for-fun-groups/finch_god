package com.finch.god.common.modules.csrf.service.impl;

import com.finch.god.common.modules.csrf.config.CsrfConfiguration;
import com.finch.god.common.modules.csrf.enums.CheckTypeEnum;
import com.finch.god.common.modules.csrf.service.CheckService;
import com.finch.god.common.modules.csrf.service.CsrfTokenService;
import com.finch.god.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class TokenCheckService implements CheckService {

    @Resource
    private CsrfConfiguration csrfConfiguration;
    @Resource
    private CsrfTokenService csrfTokenService;

    @Override
    public boolean check(HttpServletRequest request) {
        String reqToken = RequestUtils.parameterValue(request, csrfConfiguration.paramName());
        String cookieToken = csrfTokenService.getToken(request);

        log.debug("check token, req_token:{}, cookie_token:{}.", reqToken, cookieToken);

        if (StringUtils.isAnyBlank(reqToken, cookieToken)) {
            return false;
        }

        if (!csrfTokenService.isValid(cookieToken)) {
            log.warn("invalid cookie_token, cookie_token:{}.", cookieToken);
            return false;
        }

        return StringUtils.equals(reqToken, cookieToken);
    }

    @Override
    public void afterCheck(HttpServletResponse response) {
        csrfTokenService.setToken(response);
    }

    @Override
    public CheckTypeEnum checkType() {
        return CheckTypeEnum.TOKEN;
    }

}
