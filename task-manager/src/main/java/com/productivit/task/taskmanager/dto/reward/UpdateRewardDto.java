package com.productivit.task.taskmanager.dto.reward;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateRewardDto {

    @NotNull(message = "Chat id couldn't be null.")
    private Long chatId;

    @NotNull(message = "Reward description couldn't be null.")
    @Size(min = 3, max = 45,
            message = "Reward description should consist of 3-45 letters.")
    private String description;

    @NotNull(message = "Reward days couldn't be null.")
    @Min(value = 1, message = "Your reward need at least one day to achieve.")
    private Integer days;
}
