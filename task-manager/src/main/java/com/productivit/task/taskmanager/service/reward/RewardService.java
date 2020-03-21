package com.productivit.task.taskmanager.service.reward;

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

@Service
@AllArgsConstructor
public class RewardService {

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
}
