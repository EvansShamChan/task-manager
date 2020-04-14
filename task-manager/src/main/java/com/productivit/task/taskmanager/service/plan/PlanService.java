package com.productivit.task.taskmanager.service.plan;

import com.productivit.task.taskmanager.dto.plan.PlanDto;
import com.productivit.task.taskmanager.dto.reward.RewardDto;
import com.productivit.task.taskmanager.entity.Plan;
import com.productivit.task.taskmanager.enums.PlanStatus;
import com.productivit.task.taskmanager.mapper.TaskDtoMapper;
import com.productivit.task.taskmanager.repository.plan.PlanRepository;
import com.productivit.task.taskmanager.repository.task.TaskRepository;
import com.productivit.task.taskmanager.service.percent.PercentService;
import com.productivit.task.taskmanager.service.reward.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class PlanService {

    private PlanRepository planRepository;

    private RewardService rewardService;

    private PercentService percentService;

    private TaskRepository taskRepository;

    public Long createPlan(String assignedDate, Long chatId) {
        Plan createdPlan = createNewPlan(assignedDate, chatId);
        return createdPlan.getId();
    }

    public Long getIdByAssignedDateAndAndChatId(String assignedDate, Long chatId) {
        Date parsedDate = null;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(assignedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return planRepository.getIdByAssignedDateAndAndChatId(parsedDate, chatId);
    }

    public PlanDto getPlan(String assignedDate, Long chatId) {
        Long planId = getIdByAssignedDateAndAndChatId(assignedDate, chatId);

        if (planId == null) {
            planId = createNewPlan(assignedDate, chatId).getId();
        }

        return PlanDto.builder()
                .percent(percentService.getCurrentPercent(chatId))
                .assignedDate(assignedDate)
                .rewardNeededDays(rewardService.getNeededDays(chatId))
                .tasks(TaskDtoMapper.mapTasks(taskRepository.getPlanTasks(planId)))
                .rewardDoneDays(0)
                .build();
    }

    private Plan createNewPlan(String assignedDate, Long chatId) {
        RewardDto activeReward = rewardService.getActiveReward(chatId);

        if (activeReward == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No active reward is found.");
        }

        Date parsedDate = null;
        try {
            parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(assignedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Plan newPlan = Plan.builder()
                .assignedDate(parsedDate)
                .chatId(chatId)
                .createdTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .status(PlanStatus.CREATED)
                .rewardId(activeReward.getId())
                .build();

        return planRepository.save(newPlan);
    }
}
