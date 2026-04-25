package com.mbs.api.exception.handler;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

/**
 * @author 'Bilol Tuxtamurodov' on 17.02.2026
 * @project Lesson_117
 * @contact @BilolTuxtamurodov
 */

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers,
        HttpStatusCode status, WebRequest request) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("timestamp", new Date());
        error.put("status", status.value());
        List<String> errors = new LinkedList<>();
        for (FieldError e : ex.getBindingResult().getFieldErrors()) {
            errors.add(e.getDefaultMessage());
        }
        error.put("errors", errors);

        return ResponseEntity.badRequest().body(error);
    }
}
