package com.productivit.task.taskmanager.dto.plan;

import com.productivit.task.taskmanager.dto.task.TaskDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanDto {

    private String assignedDate;

    private Integer rewardNeededDays;

    private Integer rewardDoneDays;

    private List<TaskDto> tasks;

    private Integer percent;
}
