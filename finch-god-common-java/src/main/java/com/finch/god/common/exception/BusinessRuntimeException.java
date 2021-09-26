package com.finch.god.common.exception;

/**
 * 业务异常
 */
public class BusinessRuntimeException extends RuntimeException {

    public BusinessRuntimeException(){
        super();
    }

    public BusinessRuntimeException(String message){
        super(message);
    }

    public BusinessRuntimeException(String message,Throwable t){
        super(message,t);
    }

}
