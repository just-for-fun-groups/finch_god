package com.finch.god.common.exception;

import com.finch.god.common.vo.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局controller异常捕获
 * 返回友好的异常格式
 */
@ControllerAdvice
public class ExceptionHandle {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    public ExceptionHandle() {
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultInfo exceptionHandle(Exception e) {
        logger.error(e.getMessage(), e);
        return ResultInfo.error("系统繁忙");
    }

    @ExceptionHandler(BusinessRuntimeException.class)
    @ResponseBody
    public ResultInfo customException(BusinessRuntimeException e) {
        logger.error(e.getMessage(), e);
        return ResultInfo.errorMsg(e.getMessage());
    }

}
