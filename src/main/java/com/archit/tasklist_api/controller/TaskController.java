package com.archit.tasklist_api.controller;

import com.archit.tasklist_api.domain.dto.TaskDto;
import com.archit.tasklist_api.domain.entities.Task;
import com.archit.tasklist_api.mappers.TaskMapper;
import com.archit.tasklist_api.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping( path = "/task-lists/{task_list_id}/tasks" )
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController( TaskService taskService, TaskMapper taskMapper ) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List< TaskDto > listTasks( @PathVariable( "task_list_id" ) UUID taskListId ) {
        return taskService.listTasks( taskListId )
                .stream()
                .map( taskMapper::toDto )
                .collect( Collectors.toList() );
    }

    @PostMapping
    public TaskDto createTask( @PathVariable( "task_list_id" ) UUID taskListId, @RequestBody TaskDto taskDto ) {
        Task createdTask = taskService.createTask( taskListId, taskMapper.fromDto( taskDto ) );
        return taskMapper.toDto( createdTask );
    }

    @GetMapping( "/{task_id}" )
    public Optional< TaskDto > getTask( @PathVariable( "task_list_id" ) UUID taskListId, @PathVariable( "task_id" ) UUID taskId ) {
        return taskService.getTask( taskListId, taskId ).map( taskMapper::toDto );
    }

    @PutMapping( "/{task_id}" )
    public TaskDto updateTask( @PathVariable( "task_list_id" ) UUID taskListId, @PathVariable( "task_id" ) UUID taskId, @RequestBody TaskDto taskDto ) {
        Task updatedTask = taskService.updateTask( taskListId, taskId, taskMapper.fromDto( taskDto ) );
        return taskMapper.toDto( updatedTask );
    }

    @DeleteMapping( "/{task_id}" )
    public void deleteTask( @PathVariable( "task_list_id" ) UUID taskListId, @PathVariable( "task_id" ) UUID taskId ) {
        taskService.deleteTask( taskListId, taskId );
    }
}
