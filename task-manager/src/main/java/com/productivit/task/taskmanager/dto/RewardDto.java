package com.productivit.task.taskmanager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RewardDto {

    private Long id;

    private String description;

    private Integer neededDays;
}
