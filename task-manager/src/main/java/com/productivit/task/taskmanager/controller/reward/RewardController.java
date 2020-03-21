package com.productivit.task.taskmanager.controller.reward;

import com.productivit.task.taskmanager.dto.UpdateRewardDto;
import com.productivit.task.taskmanager.service.reward.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reward")
@AllArgsConstructor
public class RewardController {

    private RewardService rewardService;

    @PostMapping("{chatId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addBlankReward(@PathVariable("chatId") Long chatId) {
        return rewardService.addBlankReward(chatId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReward(@RequestBody UpdateRewardDto updateRewardDto) {
        rewardService.updateReward(updateRewardDto);
    }

    // todo: add check if reward belongs to this chatId
    @DeleteMapping("{rewardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReward(@PathVariable("rewardId") Long rewardId) {
        rewardService.deleteById(rewardId);
    }
}
