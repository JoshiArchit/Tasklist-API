package com.archit.tasklist_api.services.impl;

import com.archit.tasklist_api.controller.GlobalExceptionHandler;
import com.archit.tasklist_api.domain.entities.TaskList;
import com.archit.tasklist_api.repositories.TaskListRepository;
import com.archit.tasklist_api.services.TaskListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;
    private final GlobalExceptionHandler exceptionHandler;

    public TaskListServiceImpl( TaskListRepository taskListRepository, GlobalExceptionHandler exceptionHandler ) {
        this.taskListRepository = taskListRepository;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public List< TaskList > listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList( TaskList taskList ) {
        if( taskList.getId() != null ) {
            throw new IllegalArgumentException( "Task List already has an ID" );
        }

        if( taskList.getTitle() == null || taskList.getTitle().isBlank() ) {
            throw new IllegalArgumentException( "Task List must have a title" );
        }

        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(
                new TaskList(
                        null,
                        taskList.getTitle(),
                        taskList.getDescription(),
                        null,
                        now,
                        now
                )
        );
    }

    @Override
    public Optional< TaskList > getTaskList( UUID taskListId ) {
        return taskListRepository.findById( taskListId );
    }

    @Override
    @Transactional
    public TaskList updateTaskList( UUID taskListId, TaskList taskList ) {
        if( taskList.getId() == null ) {
            throw new IllegalArgumentException( "Task List must have and ID." );
        }

        if( !Objects.equals( taskList.getId(), taskListId ) ) {
            throw new IllegalArgumentException( "Attempting to change Task List ID is not permitted" );
        }

        // Get tasklist
        TaskList fetchedTaskList = taskListRepository.findById( taskListId )
                .orElseThrow( () -> new IllegalArgumentException( "Task List with ID " + taskListId + " not found." ) );

        fetchedTaskList.setTitle( taskList.getTitle() );
        fetchedTaskList.setDescription( taskList.getDescription() );
        fetchedTaskList.setUpdated( LocalDateTime.now() );
        return taskListRepository.save( fetchedTaskList );
    }

    @Override
    public void deleteTaskList( UUID taskListId ) {
        taskListRepository.deleteById( taskListId );
    }
}
