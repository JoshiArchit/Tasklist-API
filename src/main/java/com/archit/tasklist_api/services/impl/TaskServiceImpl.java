package com.archit.tasklist_api.services.impl;

import com.archit.tasklist_api.domain.entities.Task;
import com.archit.tasklist_api.domain.entities.TaskList;
import com.archit.tasklist_api.domain.enums.TaskPriority;
import com.archit.tasklist_api.domain.enums.TaskStatus;
import com.archit.tasklist_api.repositories.TaskListRepository;
import com.archit.tasklist_api.repositories.TaskRepository;
import com.archit.tasklist_api.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl( TaskRepository taskRepository, TaskListRepository taskListRepository ) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List< Task > listTasks( UUID taskListId ) {
        return taskRepository.findByTaskListId( taskListId );
    }

    @Override
    @Transactional
    public Task createTask( UUID taskListId, Task task ) {
        // Check task ID is null
        if( task.getId() != null ) {
            throw new IllegalArgumentException( "Task Already has an ID!" );
        }

        if( task.getTitle() == null || task.getTitle().isBlank() ) {
            throw new IllegalArgumentException( "Tasks must have a title!" );
        }

        // Set taskPriority, taskStatus and time
        TaskPriority taskPriority = Optional.ofNullable( task.getPriority() ).orElse( TaskPriority.MEDIUM );
        TaskStatus taskStatus = TaskStatus.OPEN;
        LocalDateTime now = LocalDateTime.now();

        // Get taskList
        TaskList taskList = taskListRepository.findById( taskListId ).orElseThrow( () -> new IllegalArgumentException( "Invalid task list ID provided!" ) );

        // Build task
        Task taskEntity = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );

        return taskRepository.save( taskEntity );
    }

    @Override
    public Optional< Task > getTask( UUID taskListId, UUID taskId ) {
        return taskRepository.findByTaskListIdAndId( taskListId, taskId );
    }

    @Override
    @Transactional
    public Task updateTask( UUID taskListId, UUID taskId, Task task ) {
        // Validate task using private helper
        this.validateTask( taskId, task );

        Task currentTask = taskRepository.findByTaskListIdAndId( taskListId, taskId )
                .orElseThrow( () -> new IllegalArgumentException( "No such task with ID :" + taskId + " found!" ) );

        // Update task
        currentTask.setTitle( task.getTitle() );
        currentTask.setDescription( task.getDescription() );
        currentTask.setDueDate( task.getDueDate() );
        currentTask.setPriority( task.getPriority() );
        currentTask.setStatus( task.getStatus() );
        currentTask.setUpdated( LocalDateTime.now() );

        return taskRepository.save( currentTask );
    }

    @Override
    @Transactional
    public void deleteTask( UUID taskListId, UUID taskId ) {
        taskRepository.deleteByTaskListIdAndId( taskListId, taskId );
    }

    private void validateTask( UUID taskId, Task task ) {
        if( task.getId() == null ) {
            throw new IllegalArgumentException( "Task ID must have an ID." );
        }

        if( !Objects.equals( task.getId(), taskId ) ) {
            throw new IllegalArgumentException( "Task ID does not match." );
        }

        if( task.getPriority() == null ) {
            throw new IllegalArgumentException( "Task must have a valid priority" );
        }

        if( task.getStatus() == null ) {
            throw new IllegalArgumentException( "Task must have a valid status" );
        }
    }
}
