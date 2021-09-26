package com.finch.god.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class MD5Utils {

    private static final Logger log = LoggerFactory.getLogger(MD5Utils.class);
    private static final String CHARSET = "UTF-8";
    private static final String DIGEST_MD5 = "MD5";
    private static final char[] md5Chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public MD5Utils() {
    }

    public static String md5(String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(input.getBytes("UTF-8"));
            byte[] digest = md5.digest();
            char[] chars = toHexChars(digest);
            return new String(chars);
        } catch (Exception var4) {
            log.error("error when md5, input:{}.", input, var4);
            throw new IllegalArgumentException(var4.getMessage());
        }
    }

    private static char[] toHexChars(byte[] digest) {
        char[] chars = new char[digest.length * 2];
        int i = 0;
        byte[] var3 = digest;
        int var4 = digest.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            char c0 = md5Chars[(b & 240) >> 4];
            chars[i++] = c0;
            char c1 = md5Chars[b & 15];
            chars[i++] = c1;
        }

        return chars;
    }

}
