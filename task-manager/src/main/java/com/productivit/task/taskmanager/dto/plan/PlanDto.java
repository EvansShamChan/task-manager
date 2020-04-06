package com.productivit.task.taskmanager.dto.plan;

import com.productivit.task.taskmanager.entity.Task;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PlanDto {

    private Date assignedDate;

    private Integer rewardNeededDays;

    private Integer rewardDoneDays;

    private List<Task> tasks;

    private Integer percent;
}
