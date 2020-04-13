package com.productivit.task.taskmanager.dto.task;

import lombok.Data;

@Data
public class AddTaskDto {

    private String description;

    private Integer points;

    private Long chatId;

    private String assignedDate;
}
