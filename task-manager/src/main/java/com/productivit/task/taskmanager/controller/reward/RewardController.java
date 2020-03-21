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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long addReward(@RequestBody UpdateRewardDto updateRewardDto) {
        return rewardService.addReward(updateRewardDto);
    }

    // todo: add check if reward belongs to this chatId
    @DeleteMapping("{rewardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReward(@PathVariable("rewardId") Long rewardId) {
        rewardService.deleteById(rewardId);
    }

    @PutMapping("{chatId}/reward/{rewardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setRewardActive(@PathVariable("chatId") Long chatId,
                                @PathVariable("rewardId") Long rewardId) {
        rewardService.setRewardActive(chatId, rewardId);
    }
}
