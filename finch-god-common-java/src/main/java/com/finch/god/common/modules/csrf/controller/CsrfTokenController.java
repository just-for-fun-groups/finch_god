package com.finch.god.common.modules.csrf.controller;

import com.finch.god.common.modules.csrf.service.CsrfTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@ConditionalOnProperty(name = "csrf.tokenUrl")
public class CsrfTokenController {

    @Resource
    private CsrfTokenService csrfTokenService;

    @GetMapping("${csrf.tokenUrl}")
    public String token(HttpServletResponse response) {
        csrfTokenService.setToken(response);
        return StringUtils.EMPTY;
    }

}
