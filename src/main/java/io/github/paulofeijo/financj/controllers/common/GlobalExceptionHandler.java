package io.github.paulofeijo.financj.controllers.common;

import io.github.paulofeijo.financj.controllers.dtos.FieldError;
import io.github.paulofeijo.financj.controllers.dtos.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var fieldErrors = e.getFieldErrors().stream()
                .map(fieldError -> new FieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value(),"Validation errors", fieldErrors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.error("Data integrity violation: {} {}", e.getMessage(), e.getStackTrace(), e);
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Data integrity violation", null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseError handleException(Exception e) {
        logger.error("Unexpected exception: {} {}", e.getMessage(), e.getStackTrace(), e);
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Unexpected exception", null);
    }
}
