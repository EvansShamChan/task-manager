package com.productivit.task.taskmanager.service.reward;

import com.productivit.task.taskmanager.dto.UpdateRewardDto;
import com.productivit.task.taskmanager.entity.Reward;
import com.productivit.task.taskmanager.enums.RewardStatus;
import com.productivit.task.taskmanager.repository.reward.RewardRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RewardService {

    private RewardRepository rewardRepository;

    public Long addBlankReward(Long chatId) {
        Reward newReward = Reward.builder()
                .status(RewardStatus.CREATED)
                .createdTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .chatId(chatId)
                .build();

        return rewardRepository.save(newReward).getId();
    }

    public void updateReward(UpdateRewardDto updateRewardDto) {
        Reward reward = rewardRepository.findById(updateRewardDto.getRewardId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reward not found."));

        if (updateRewardDto.getDescription() != null) {
            reward.setDescription(updateRewardDto.getDescription());
        } else if (updateRewardDto.getDays() != null) {
            reward.setNeededDays(updateRewardDto.getDays());
        } else return;

        rewardRepository.save(reward);
    }
}
