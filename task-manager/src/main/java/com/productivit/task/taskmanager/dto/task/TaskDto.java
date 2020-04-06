package com.productivit.task.taskmanager.dto.task;

import lombok.Data;

@Data
public class TaskDto {

    private Long id;

    private String description;

    private Integer points;

    private Integer donePoints;
}
