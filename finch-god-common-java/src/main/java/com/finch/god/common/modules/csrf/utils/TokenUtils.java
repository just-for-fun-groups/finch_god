package com.finch.god.common.modules.csrf.utils;

import com.finch.god.common.config.Constants;
import com.finch.god.common.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class TokenUtils {

    private static final String SR = "-";
    private static final int LEN_KEY = 32;

    public static String generate() {
        String key = generateKey();
        return key + sign(key);
    }

    public static boolean isValid(String token) {
        String key = StringUtils.substring(token, 0, LEN_KEY);
        String sign = StringUtils.substring(token, LEN_KEY);

        return StringUtils.equals(sign(key), sign);
    }

    private static String appendSign(String key) {
        return key + MD5Utils.md5(key + Constants.SALT_TOKEN_SIGN);
    }

    private static String generateKey() {
        return UUID.randomUUID().toString().replaceAll(SR, StringUtils.EMPTY);
    }

    private static String sign(String key) {
        return MD5Utils.md5(key + Constants.SALT_TOKEN_SIGN);
    }

}
