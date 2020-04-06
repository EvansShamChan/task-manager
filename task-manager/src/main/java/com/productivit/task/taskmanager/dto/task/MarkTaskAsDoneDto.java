package com.productivit.task.taskmanager.dto.task;

import lombok.Data;

import java.util.Date;

@Data
public class MarkTaskAsDoneDto {

    private Long chatId;

    private Date assignedDate;

    private Long taskId;

    private Integer donePoints;
}
