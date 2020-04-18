package com.productivit.task.taskmanager.service.reward;

import com.productivit.task.taskmanager.dto.reward.RewardDto;
import com.productivit.task.taskmanager.dto.reward.UpdateRewardDto;
import com.productivit.task.taskmanager.entity.Reward;
import com.productivit.task.taskmanager.enums.RewardStatus;
import com.productivit.task.taskmanager.projection.RewardDaysProjection;
import com.productivit.task.taskmanager.repository.plan.PlanRepository;
import com.productivit.task.taskmanager.repository.reward.RewardRepository;
import com.productivit.task.taskmanager.service.plan.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RewardService {

    private static final Integer REWARDS_PER_PAGE = 5;

    private RewardRepository rewardRepository;

    private PlanRepository planRepository;

    public Long addReward(UpdateRewardDto updateRewardDto) {
        Reward newReward = Reward.builder()
                .status(RewardStatus.CREATED)
                .createdTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .chatId(updateRewardDto.getChatId())
                .description(updateRewardDto.getDescription())
                .neededDays(updateRewardDto.getDays())
                .doneDays(0)
                .build();

        Reward createdReward = rewardRepository.save(newReward);

        rewardRepository.findActiveReward(updateRewardDto.getChatId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.MULTI_STATUS, createdReward.getId().toString()));

        return createdReward.getId();
    }

    public void deleteById(Long rewardId) {
        rewardRepository.deleteById(rewardId);
    }

    @Transactional
    public void setRewardActive(Long chatId, Long rewardId) {
        Reward activeReward = rewardRepository.findActiveReward(chatId).get();
        if (activeReward != null) {
            activeReward.setStatus(RewardStatus.CREATED);
            rewardRepository.save(activeReward);
        }
        planRepository.updatePlanKeysOnRewardActivation(chatId, rewardId);
        rewardRepository.setActiveReward(chatId, rewardId);
    }

    public RewardDto getActiveReward(Long chatId) {
        Reward activeReward = rewardRepository.findActiveReward(chatId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return RewardDto.builder()
                .id(activeReward.getId())
                .description(activeReward.getDescription())
                .neededDays(activeReward.getNeededDays())
                .build();
    }

    public List<RewardDto> getRewards(Long chatId, Integer currentPage) {
        Integer offset = currentPage * REWARDS_PER_PAGE;
        List<Reward> rewardsForPage = rewardRepository.getRewardsForPage(chatId, REWARDS_PER_PAGE, offset);

        return rewardsForPage.stream().map(reward ->
                RewardDto.builder()
                        .id(reward.getId())
                        .description(reward.getDescription())
                        .neededDays(reward.getNeededDays())
                        .doneDays(reward.getDoneDays())
                        .build())
                .collect(Collectors.toList());
    }

    public RewardDaysProjection getDaysInfo(Long chatId) {
        return rewardRepository.getDaysInfo(chatId);
    }

    public void incrementDoneDays(Long chatId) {
        rewardRepository.incrementDoneDays(chatId);
    }
}
