package com.productivit.task.taskmanager.controller.reward;

import com.productivit.task.taskmanager.entity.Reward;
import com.productivit.task.taskmanager.service.reward.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("active/{chatId}")
    public Reward getActiveReward(@PathVariable("chatId") Long chatId) {
        return rewardService.getActiveReward(chatId);
    }

    @GetMapping("{chatId}")
    public List<Reward> getAllRewards(@PathVariable("chatId") Long chatId) {
        return rewardService.getAllRewards(chatId);
    }

    @PutMapping("active/{chatId}/reward/{rewardId}")
    public void setActiveReward(@PathVariable("chatId") Long chatId,
                                @PathVariable("rewardId") Long rewardId) {
        rewardService.setActiveReward(chatId, rewardId);
    }
}
