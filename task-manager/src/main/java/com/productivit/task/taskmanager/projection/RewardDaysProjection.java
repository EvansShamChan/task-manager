package com.productivit.task.taskmanager.projection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardDaysProjection {

    private Integer neededDays;

    private Integer doneDays;
}
