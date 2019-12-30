package com.issuefinder.crawling.exception;

import com.issuefinder.crawling.contants.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class EndpointAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse exceptionResourceNotFound(ResourceNotFoundException e) {
        return new ErrorResponse(ErrorCode.NOT_FOUND.name() , e.getMessage());
    }

    @ExceptionHandler(JsoupConnectionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionJsoupConnection(ResourceNotFoundException e) {
        return new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR.name() , e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse exceptionMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        return new ErrorResponse(ErrorCode.BAD_REQUEST.name(), e.getMessage());
    }

    @ExceptionHandler(ExternalApiException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse externalApiException(ExternalApiException e) {
        return new ErrorResponse(e.getErrorCode().name() , e.getMessage());
    }
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse exceptionMissingServletRequestParameter(MissingServletRequestParameterException e) {
//        return new ErrorResponse(ErrorCode.BAD_REQUEST.name(), e.getMessage());
//    }

//    @ExceptionHandler(Throwable.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ErrorResponse uncaughtException(Throwable e) {
//        log.info("getStackTrace : {}" , e.getStackTrace());
//        return new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR.name(), e.getMessage());
//    }

}
