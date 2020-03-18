package com.productivit.task.taskmanager.entity;

import com.productivit.task.taskmanager.enums.RewardStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reward {

    private Long id;

    private String description;

    private Integer neededDays;

    private RewardStatus status;

    private Long chatId;
}
