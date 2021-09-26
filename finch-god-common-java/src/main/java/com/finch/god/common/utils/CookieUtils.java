package com.finch.god.common.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

public class CookieUtils {

    public CookieUtils() {
    }

    public static String getValue(HttpServletRequest request, String name) {
        return ArrayUtils.isEmpty(request.getCookies()) ? "" : (String) Stream.of(request.getCookies()).filter((c) -> {
            return StringUtils.equals(c.getName(), name);
        }).map(Cookie::getValue).findFirst().orElse("");
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int expire) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }

}
