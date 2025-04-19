package com.archit.tasklist_api.mappers.Impl;

import com.archit.tasklist_api.domain.dto.TaskDto;
import com.archit.tasklist_api.domain.entities.Task;
import com.archit.tasklist_api.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto( TaskDto taskDto ) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto( Task task ) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus(),
                task.getUpdated()
        );
    }
}
