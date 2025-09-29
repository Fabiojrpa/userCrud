package com.fabio.userCrud.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        String message,
        List<String> errors,
        int status,
        LocalDateTime timestamp
) {
    public ApiError(String message, int status) {
        this(message, List.of(), status, LocalDateTime.now());
    }

    public ApiError(String message, List<String> errors, int status) {
        this(message, errors, status, LocalDateTime.now());
    }
}

