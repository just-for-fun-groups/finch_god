package com.finch.god.common.modules.csrf.service;

import com.finch.god.common.modules.csrf.enums.CheckTypeEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CheckService {

    boolean check(HttpServletRequest request);

    void afterCheck(HttpServletResponse response);

    CheckTypeEnum checkType();

}
