package com.productivit.task.taskmanager.service.plan;

import com.productivit.task.taskmanager.dto.plan.PlanDto;
import com.productivit.task.taskmanager.dto.reward.RewardDto;
import com.productivit.task.taskmanager.entity.Plan;
import com.productivit.task.taskmanager.enums.PlanStatus;
import com.productivit.task.taskmanager.repository.plan.PlanRepository;
import com.productivit.task.taskmanager.service.reward.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@AllArgsConstructor
public class PlanService {

    private PlanRepository planRepository;

    private RewardService rewardService;

    public Long createPlan(Date assignedDate, Long chatId) {
        RewardDto activeReward = rewardService.getActiveReward(chatId);

        if (activeReward == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No active reward is found.");
        }

        Plan newPlan = Plan.builder()
                .assignedDate(assignedDate)
                .chatId(chatId)
                .createdTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .status(PlanStatus.CREATED)
                .rewardId(activeReward.getId())
                .build();

        Plan createdPlan =  planRepository.save(newPlan);
        return createdPlan.getId();
    }

    public Long getIdByAssignedDateAndAndChatId(Date assignedDate, Long chatId) {
        return planRepository.getIdByAssignedDateAndAndChatId(assignedDate, chatId);
    }

    public PlanDto getPlan(Date assignedDate, Long chatId) {
        return null;
    }
}
