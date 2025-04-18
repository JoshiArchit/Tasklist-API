package com.archit.tasklist_api.mappers;

import com.archit.tasklist_api.domain.dto.TaskListDto;
import com.archit.tasklist_api.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto( TaskListDto taskListDto );

    TaskListDto toDto( TaskList taskList );
}
