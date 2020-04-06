package com.productivit.task.taskmanager.dto.task;

import lombok.Data;

import java.util.Date;

@Data
public class AddTaskDto {

    private String description;

    private Integer points;

    private Long chatId;

    private Date assignedDate;
}
