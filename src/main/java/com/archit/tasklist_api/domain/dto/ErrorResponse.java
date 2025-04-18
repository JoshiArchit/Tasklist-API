package com.archit.tasklist_api.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
