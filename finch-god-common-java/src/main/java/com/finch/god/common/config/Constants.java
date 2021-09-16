package com.finch.god.common.config;

import org.springframework.util.Base64Utils;

/**
 * 系统常量类
 */
public class Constants {

    /**
     * 默认起始页
     */
    public static final Integer DEFAULT_PAGE_START = 1;

    /**
     * 默认分页大小
     */
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 成功状态码
     */
    public static final Integer RESULT_CODE_SUCCESS = 0;
    public static final String RESULT_SUCCESS_MSG = "操作成功";

    /**
     * 默认失败状态码
     */
    public static final Integer RESULT_CODE_ERROR = -1;
    public static final String RESULT_ERROR_MSG = "操作失败";

    public static final String SESSION_UID_KEY = "SESSION_UID_KEY";
    public static final String SESSION_NICKNAME_KEY = "SESSION_NICKNAME_KEY";


    public static String APP_ID = null;
    public static String APP_CODE = null;
    public static String APPLICATION_KEY = null;
    public static String IMG_TEMP_DIRECTORY = null;
    public static String IMG_THUMB = null;
    public static String PHP_IMAGE_UPLOAD = null;
    public static String FILE_WHITE_LIST = null;
    public static String FILE_BLACK_LIST = null;

    public static String getAppCode() {
        return new String(Base64Utils.decodeFromString(APP_CODE));
    }
}
