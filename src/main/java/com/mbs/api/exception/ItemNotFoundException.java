package com.mbs.api.exception;

/**
 * @author 'Bilol Tuxtamurodov' on 17.02.2026
 * @project Lesson_117
 * @contact @BilolTuxtamurodov
 */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
