package com.finch.god.common.exception;

public class ExceptionCast {

    public static void cast(String codeDesc){
        throw new BusinessRuntimeException(codeDesc);
    }

}
