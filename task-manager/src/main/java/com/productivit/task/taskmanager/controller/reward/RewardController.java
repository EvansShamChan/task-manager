package com.productivit.task.taskmanager.controller.reward;

import com.productivit.task.taskmanager.entity.Reward;
import com.productivit.task.taskmanager.service.reward.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reward")
@AllArgsConstructor
public class RewardController {

    private RewardService rewardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addWord(@RequestBody Reward reward) {
        rewardService.addReward(reward);
    }
}
