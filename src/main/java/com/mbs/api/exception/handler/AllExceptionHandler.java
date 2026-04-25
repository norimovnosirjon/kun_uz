package com.mbs.api.exception.handler;

import com.mbs.api.dto.base.ApiResponse;
import com.mbs.api.exception.AppBadRequestException;
import com.mbs.api.exception.ItemAlreadyExistsException;
import com.mbs.api.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 'Bilol Tuxtamurodov' on 17.02.2026
 * @project Lesson_117
 * @contact @BilolTuxtamurodov
 */

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<?> handleNotFound(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.notFound(e.getMessage()));
    }

    @ExceptionHandler({AppBadRequestException.class, ItemAlreadyExistsException.class})
    public ResponseEntity<?> handleBadRequest(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.badRequest(e.getMessage()));
    }
}
