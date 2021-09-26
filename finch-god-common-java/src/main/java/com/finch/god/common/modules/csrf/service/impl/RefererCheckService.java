package com.finch.god.common.modules.csrf.service.impl;

import com.finch.god.common.modules.csrf.config.CsrfConfiguration;
import com.finch.god.common.modules.csrf.enums.CheckTypeEnum;
import com.finch.god.common.modules.csrf.service.CheckService;
import com.finch.god.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class RefererCheckService implements CheckService {

    @Resource
    private CsrfConfiguration csrfConfiguration;

    @Override
    public boolean check(HttpServletRequest request) {
        return isRefererValid(RequestUtils.referer(request));
    }

    @Override
    public void afterCheck(HttpServletResponse response) {
    }

    @Override
    public CheckTypeEnum checkType() {
        return CheckTypeEnum.REFERER;
    }

    private boolean isRefererValid(String referer) {
        if (StringUtils.isBlank(referer)) {
            log.warn("check is_referer_valid, referer is blank.");
            return false;
        }

        try {
            String refererHost = new URI(referer).getHost();
            List<String> configRefererHosts = csrfConfiguration.refererHosts();

            log.debug("check is_referer_valid, referer:{}, referer_host:{}, config_referer_hosts:{}.",
                    refererHost, refererHost, configRefererHosts);

            return configRefererHosts.contains(refererHost);
        } catch (Exception e) {
            log.error("error when checking referer, referer:{}.", referer, e);
        }

        return false;
    }

}
