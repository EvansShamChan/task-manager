package com.productivit.task.taskmanager.service.reward;

import com.productivit.task.taskmanager.entity.Reward;
import com.productivit.task.taskmanager.enums.RewardStatus;
import com.productivit.task.taskmanager.repository.reward.RewardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RewardService {

    private RewardRepository rewardRepository;

    public void addReward(Reward reward) {
        rewardRepository.addNewReward(reward);
    }

    public Reward getActiveReward(Long chatId) {
        return rewardRepository.getActiveReward(chatId);
    }

    public List<Reward> getAllRewards(Long chatId) {
        return rewardRepository.getAllRewards(chatId);
    }

    @Transactional
    public void setActiveReward(Long chatId, Long rewardId) {
        rewardRepository.disableActiveReward(chatId);
        rewardRepository.setRewardStatus(rewardId, RewardStatus.ACTIVE);
    }
}
