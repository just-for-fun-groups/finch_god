package com.finch.god.common.modules.csrf.exception;

public class CsrfException extends RuntimeException {

    public CsrfException() {
        super();
    }

    public CsrfException(String message) {
        super(message);
    }

}
