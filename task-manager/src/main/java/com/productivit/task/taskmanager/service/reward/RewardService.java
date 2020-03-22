package com.productivit.task.taskmanager.service.reward;

import com.productivit.task.taskmanager.dto.RewardDto;
import com.productivit.task.taskmanager.dto.UpdateRewardDto;
import com.productivit.task.taskmanager.entity.Reward;
import com.productivit.task.taskmanager.enums.RewardStatus;
import com.productivit.task.taskmanager.repository.reward.RewardRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    public Long addReward(UpdateRewardDto updateRewardDto) {
        Reward newReward = Reward.builder()
                .status(RewardStatus.CREATED)
                .createdTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .chatId(updateRewardDto.getChatId())
                .description(updateRewardDto.getDescription())
                .neededDays(updateRewardDto.getDays())
                .build();

        Reward createdReward = rewardRepository.save(newReward);

        Reward activeReward = rewardRepository.findActiveReward(updateRewardDto.getChatId());

        if (activeReward == null) {
            throw new ResponseStatusException(HttpStatus.MULTI_STATUS, createdReward.getId().toString());
        } else {
            return createdReward.getId();
        }
    }

    public void deleteById(Long rewardId) {
        rewardRepository.deleteById(rewardId);
    }

    public void setRewardActive(Long chatId, Long rewardId) {
        rewardRepository.setActiveReward(chatId, rewardId);
    }

    public RewardDto getActiveReward(Long chatId) {
        Reward activeReward = rewardRepository.findActiveReward(chatId);

        return RewardDto.builder()
                .id(activeReward.getId())
                .description(activeReward.getDescription())
                .neededDays(activeReward.getNeededDays())
                .build();
    }

    public List<RewardDto> getRewards(Long chatId, Integer currentPage) {
        Integer offset = currentPage * REWARDS_PER_PAGE;
        List<Reward> rewardsForPage = rewardRepository.getRewardsForPage(chatId, REWARDS_PER_PAGE, offset);
        System.out.println(rewardsForPage.stream().map(Reward::getId).collect(Collectors.toList()));

        return rewardsForPage.stream().map(reward ->
                RewardDto.builder()
                        .id(reward.getId())
                        .description(reward.getDescription())
                        .neededDays(reward.getNeededDays())
                        .build())
                .collect(Collectors.toList());
    }
}
