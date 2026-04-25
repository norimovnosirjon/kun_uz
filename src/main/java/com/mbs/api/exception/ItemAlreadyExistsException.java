package com.mbs.api.exception;

/**
 * @author 'Bilol Tuxtamurodov' on 17.02.2026
 * @project Lesson_117
 * @contact @BilolTuxtamurodov
 */
public class ItemAlreadyExistsException extends RuntimeException {
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
