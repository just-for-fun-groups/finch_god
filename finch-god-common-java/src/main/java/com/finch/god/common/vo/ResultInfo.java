package com.finch.god.common.vo;

import java.io.Serializable;

public class ResultInfo implements Serializable {

    private static final long serialVersionUID = 3670420435118433645L;

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

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态码对应信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public ResultInfo() {
        super();
    }

    public ResultInfo(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(Integer code, String msg, Object data) {
        this(code, msg);
        this.data = data;
    }

    public static ResultInfo success(Object data) {
        return new ResultInfo(RESULT_CODE_SUCCESS, RESULT_SUCCESS_MSG, data);
    }

    public static ResultInfo error(Object data) {
        return new ResultInfo(RESULT_CODE_ERROR, RESULT_ERROR_MSG, data);
    }

    public static ResultInfo errorMsg(String msg) {
        return new ResultInfo(RESULT_CODE_ERROR, msg);
    }

    public static ResultInfo error(Integer code, String msg) {
        return new ResultInfo(RESULT_CODE_ERROR, msg);
    }

    public static ResultInfo error(String msg, Object data) {
        return new ResultInfo(RESULT_CODE_ERROR, msg, data);
    }

    public static ResultInfo error(Integer code, String msg, Object data) {
        return new ResultInfo(code, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
