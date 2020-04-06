package com.productivit.task.taskmanager.controller.reward;

import com.productivit.task.taskmanager.dto.reward.RewardDto;
import com.productivit.task.taskmanager.dto.reward.UpdateRewardDto;
import com.productivit.task.taskmanager.service.reward.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("reward")
@AllArgsConstructor
public class RewardController {

    private RewardService rewardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long addReward(@Valid @RequestBody UpdateRewardDto updateRewardDto) {
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

    @GetMapping("active/{chatId}")
    public RewardDto getActiveReward(@PathVariable("chatId") Long chatId) {
        return rewardService.getActiveReward(chatId);
    }

    @GetMapping("{chatId}/page/{currentPage}")
    public List<RewardDto> getRewards(@PathVariable("chatId") Long chatId,
                                      @PathVariable("currentPage") Integer currentPage) {
        return rewardService.getRewards(chatId, currentPage);
    }
}
