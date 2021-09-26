package com.finch.god.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

    private static final Logger log = LoggerFactory.getLogger(RequestUtils.class);
    private static final String HN_REFERER = "referer";

    public RequestUtils() {
    }

    public static String headerValue(HttpServletRequest request, String name) {
        return request.getHeader(name);
    }

    public static String parameterValue(HttpServletRequest request, String name) {
        return request.getParameter(name);
    }

    public static String referer(HttpServletRequest request) {
        return request.getHeader("referer");
    }

}
