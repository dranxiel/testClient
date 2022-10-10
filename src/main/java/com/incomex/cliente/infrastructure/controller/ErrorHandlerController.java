package com.incomex.cliente.infrastructure.controller;


import com.incomex.cliente.application.ApplicationException;
import com.incomex.cliente.application.dto.out.ErrorResponse;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Controlador para el manejo de errores.
 * */
@Log
@ControllerAdvice
public class ErrorHandlerController {
    /**
     * Guardar el error no controlado y lo retorna.
     * */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> exceptionHandler(HttpServletRequest request, Exception e) {
        log.warning("Error no controlado. Mensaje:" + e.getMessage());
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    /**
     * Guardar el error controlado y retorna un estados 200 para evitar afectar a la nube por los controles de errores.
     * */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> exceptionApplicationHandler(HttpServletRequest request, ApplicationException e) {
        log.info("Error: Code" + e.getCode() + " , mensaje:" + e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(e.getCode(), e.getMessage()), HttpStatus.ACCEPTED);
    }

}
