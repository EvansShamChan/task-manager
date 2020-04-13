package com.productivit.task.taskmanager.dto.task;

import lombok.Data;

@Data
public class MarkTaskAsDoneDto {

    private Long chatId;

    private Long taskId;

    private Integer donePoints;
}
