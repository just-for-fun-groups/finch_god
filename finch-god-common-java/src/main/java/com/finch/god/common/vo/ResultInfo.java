package com.finch.god.common.vo;

import java.io.Serializable;

public class ResultInfo implements Serializable {

    private static final long serialVersionUID = 3670420435118433645L;

    /**
     * 成功状态码
     */
    public static final Integer RESULT_CODE_SUCCESS =20000;
    public static final String RESULT_SUCCESS_MSG = "操作成功";

    /**
     * 默认失败状态码
     */
    public static final Integer RESULT_CODE_ERROR = -1;
    public static final String RESULT_ERROR_MSG = "操作失败";

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态码对应信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public ResultInfo() {
        super();
    }

    public ResultInfo(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResultInfo(Integer code, String message, Object data) {
        this(code, message);
        this.data = data;
    }

    public static ResultInfo success(Object data) {
        return new ResultInfo(RESULT_CODE_SUCCESS, RESULT_SUCCESS_MSG, data);
    }

    public static ResultInfo error(Object data) {
        return new ResultInfo(RESULT_CODE_ERROR, RESULT_ERROR_MSG, data);
    }

    public static ResultInfo errorMsg(String message) {
        return new ResultInfo(RESULT_CODE_ERROR, message);
    }

    public static ResultInfo error(Integer code, String message) {
        return new ResultInfo(RESULT_CODE_ERROR, message);
    }

    public static ResultInfo error(String message, Object data) {
        return new ResultInfo(RESULT_CODE_ERROR, message, data);
    }

    public static ResultInfo error(Integer code, String message, Object data) {
        return new ResultInfo(code, message, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
