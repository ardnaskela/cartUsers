package com.cartdemo.user.controller;

import com.cartdemo.user.dto.ResponseDto;
import com.cartdemo.user.exception.NoDataFoundException;
import com.cartdemo.user.exception.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static com.cartdemo.user.Constants.*;

@ControllerAdvice
@Log4j2
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private HttpHeaders header = new HttpHeaders();

    public ControllerAdvisor() {
        header.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }


    @ExceptionHandler({NoDataFoundException.class, UserNotFoundException.class})
    @ResponseBody
    public ResponseEntity<ResponseDto> handleUserNotFoundException(
            RuntimeException ex, WebRequest request) {
        ResponseDto response = new ResponseDto(KO, NO_DATA, LocalDateTime.now(), null);
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(response);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<ResponseDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                   WebRequest request) {
        ResponseDto response = new ResponseDto(KO, NOT_SAVED, LocalDateTime.now(), null);
        log.error(ex.getMostSpecificCause());
        return ResponseEntity.status(HttpStatus.CONFLICT).headers(header).body(response);
    }

    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        ResponseDto response = new ResponseDto(KO, errors, LocalDateTime.now(), null);
        log.error(String.format("Status: %s, errors: %s", status.toString(), errors));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(header).body(response);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = String.format("%s: %s %s", NO_HANDLER, ex.getHttpMethod(), ex.getRequestURL());
        ResponseDto response = new ResponseDto(KO, error, LocalDateTime.now(), null);
        log.error(error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(header).body(response);
    }
}
