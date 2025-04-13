package com.archit.tasklist_api.domain.entities;

import com.archit.tasklist_api.domain.enums.TaskPriority;
import com.archit.tasklist_api.domain.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table( name = "tasks" )
public class Task {

    @Id
    @GeneratedValue( strategy = GenerationType.UUID )
    @Column( name = "id", updatable = false, nullable = false )
    private UUID id;

    @Column( name = "id", nullable = false )
    private String title;

    @Column( name = "description" )
    private String description;

    @Column( name = "due_date" )
    private LocalDateTime dueDate;

    @Column( name = "status" )
    private TaskStatus status;

    @Column( name = "priority" )
    private TaskPriority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    private TaskList taskList;

    @Column( name = "created", nullable = false )
    private LocalDateTime created;

    @Column( name = "updated", nullable = false )
    private LocalDateTime updated;
}
