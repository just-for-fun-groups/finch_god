package com.finch.god.common.modules.csrf.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CsrfTokenService {

    String getToken(HttpServletRequest request);

    void setToken(HttpServletResponse response);

    boolean isValid(String token);

}
