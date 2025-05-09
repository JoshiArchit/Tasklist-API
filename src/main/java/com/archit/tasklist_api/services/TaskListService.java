package com.archit.tasklist_api.services;

import com.archit.tasklist_api.domain.entities.TaskList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List< TaskList > listTaskLists();

    TaskList createTaskList( TaskList taskList );

    Optional< TaskList > getTaskList( UUID taskListId );

    TaskList updateTaskList( UUID taskListId, TaskList taskList );

    void deleteTaskList( UUID taskListId );
}
