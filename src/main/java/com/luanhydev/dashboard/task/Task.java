package com.luanhydev.dashboard.task;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Task")
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "task",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String task;

    @Column(
            name = "is_completed",
            nullable = false
    )
    private Boolean isCompleted;

    @Column(
            name = "user_id",
            nullable = false
    )
    private String userId;

    public Task(String task, Boolean isCompleted, String userId) {
        this.task = task;
        this.isCompleted = isCompleted;
        this.userId = userId;
    }
}
