package com.incomex.cliente.application;


import com.incomex.cliente.domain.ErrorType;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ApplicationException extends RuntimeException implements Serializable {
    private String code;
    private String message;

    public ApplicationException(ErrorType errorType) {
        code = errorType.getCode();
        message = errorType.getMessage();
    }
    public ApplicationException(ErrorType errorType, String message) {
        code = errorType.getCode();
        this.message = errorType.getMessage() + message;
    }
}
