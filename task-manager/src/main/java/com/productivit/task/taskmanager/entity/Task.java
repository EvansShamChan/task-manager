package com.productivit.task.taskmanager.entity;

import com.productivit.task.taskmanager.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "points")
    private Integer points;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdTimestamp;

    @Column(name = "updated_date", nullable = false)
    private Timestamp updatedTimestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "done_points")
    private Integer donePoints;
}
