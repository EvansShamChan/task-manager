package com.productivit.task.taskmanager.dto;

import lombok.Data;

@Data
public class UpdateRewardDto {

    private Long chatId;

    private String description;

    private Integer days;
}
