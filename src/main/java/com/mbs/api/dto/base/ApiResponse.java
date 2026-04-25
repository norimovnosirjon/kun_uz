package com.mbs.api.dto.base;

/**
 * @author 'Bilol Tuxtamurodov' on 07.04.2026
 * @project Lesson_134_kun_uz
 * @contact @BilolTuxtamurodov
 */
public class ApiResponse<T> {
    String message;
    Integer code;
    Boolean isError;
    T data;

    public ApiResponse(String message, Integer code, Boolean isError, T data) {
        this.message = message;
        this.code = code;
        this.isError = isError;
        this.data = data;
    }

    public ApiResponse(String message, Integer code, Boolean isError) {
        this.message = message;
        this.code = code;
        this.isError = isError;
    }

    public ApiResponse() {

    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>("Bajarildi", 200, false);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("Bajarildi", 200, false, data);
    }

    public static <T> ApiResponse<T> badRequest(String message, Integer code) {
        return new ApiResponse<>(message, code, true);
    }

    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(message, 400, true);
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return new ApiResponse<>(message, 404, true);
    }




}
