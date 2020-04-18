package com.productivit.task.taskmanager.dto.plan;

import lombok.Data;

@Data
public class MarkPlanAsFinishedDto {

    private String assignedDate;

    private Long chatId;

    private Integer donePercentage;
}
