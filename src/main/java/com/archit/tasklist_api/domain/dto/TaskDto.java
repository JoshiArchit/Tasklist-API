package com.archit.tasklist_api.domain.dto;

import com.archit.tasklist_api.domain.enums.TaskPriority;
import com.archit.tasklist_api.domain.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
