package com.finch.god.common.modules.csrf.service.impl;

import com.finch.god.common.modules.csrf.config.CsrfConfiguration;
import com.finch.god.common.modules.csrf.enums.CheckTypeEnum;
import com.finch.god.common.modules.csrf.service.CheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@Slf4j
public class CheckContext {

    @Resource
    private List<CheckService> checkServiceList;
    @Resource
    private CsrfConfiguration csrfConfiguration;

    public boolean check(HttpServletRequest request, HttpServletResponse response) {
        boolean res = csrfConfiguration.checkTypes().stream().map(c -> doCheck(c, request))
                .filter(cr -> !cr).findFirst().orElse(true);

        if (res) {
            csrfConfiguration.checkTypes().forEach(c -> doAfterCheck(c, response));
        }

        return res;
    }

    private boolean doCheck(CheckTypeEnum checkType, HttpServletRequest request) {
        try {
            return checkServiceList.stream().filter(c -> checkType == c.checkType())
                    .map(c -> c.check(request)).findFirst().orElse(false);
        } catch (Exception e) {
            log.error("error when checking, check_type:{}.", checkType, e);
        }

        return false;
    }

    private void doAfterCheck(CheckTypeEnum checkType, HttpServletResponse response) {
        try {
            checkServiceList.stream().filter(c -> checkType == c.checkType())
                    .forEach(c -> c.afterCheck(response));
        } catch (Exception e) {
            log.error("error when do_after_check, check_type:{}.", checkType, e);
        }
    }

}
