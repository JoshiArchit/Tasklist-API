package com.archit.tasklist_api.mappers;

import com.archit.tasklist_api.domain.dto.TaskDto;
import com.archit.tasklist_api.domain.entities.Task;

public interface TaskMapper {
    Task fromDto( TaskDto taskDto );

    TaskDto toDto( Task task );
}
