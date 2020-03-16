package com.productivit.task.taskmanager.service.reward;

import com.productivit.task.taskmanager.entity.Reward;
import com.productivit.task.taskmanager.repository.reward.RewardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RewardService {

    private RewardRepository rewardRepository;

    public void addReward(Reward reward) {
        rewardRepository.addNewReward(reward);
    }
}
