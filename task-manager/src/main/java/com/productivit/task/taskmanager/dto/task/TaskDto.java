package com.productivit.task.taskmanager.dto.task;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskDto {

    private Long id;

    private String description;

    private Integer points;

    private Integer donePoints;
}
