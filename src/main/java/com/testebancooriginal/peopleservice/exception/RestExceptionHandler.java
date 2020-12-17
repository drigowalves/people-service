package com.testebancooriginal.peopleservice.exception;

import static java.util.stream.Collectors.joining;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;

@ControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final ObjectMapper mapper;

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<?> handleConflict(RuntimeException ex, WebRequest request)
            throws JsonProcessingException {

        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),
                responseStatus.code().value());

//        String bodyOfResponse = mapper.writeValueAsString(exceptionResponse);
        return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(),
                responseStatus.code(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String messages = fieldErrors.stream()
            .filter(Objects::nonNull)
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(joining(", "));

        return new ResponseEntity<>(new ExceptionResponse(messages, status.value()), status);
    }
}
