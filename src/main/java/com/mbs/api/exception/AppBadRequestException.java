package com.mbs.api.exception;

/**
 * @author 'Bilol Tuxtamurodov' on 26.02.2026
 * @project Lesson_117
 * @contact @BilolTuxtamurodov
 */
public class AppBadRequestException extends RuntimeException {
    public AppBadRequestException(String message) {
        super(message);
    }
}
