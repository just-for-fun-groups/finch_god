package com.finch.god.common.exception;

import com.finch.god.common.vo.ResultInfo;

public class XssException extends RuntimeException{

    private Integer code;
    private String msg;

    public XssException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public XssException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public String getOriginalMessage() {
        return msg;
    }

    public ResultInfo toResult() {
        return new ResultInfo(code, msg);
    }

}
