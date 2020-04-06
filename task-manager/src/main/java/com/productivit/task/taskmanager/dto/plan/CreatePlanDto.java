package com.productivit.task.taskmanager.dto.plan;

import lombok.Data;

import java.util.Date;

@Data
public class CreatePlanDto {

    private Long chatId;

    private Date assignedDate;
}
