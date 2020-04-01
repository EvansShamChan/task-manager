package com.productivit.task.taskmanager.controller.percent;

import com.productivit.task.taskmanager.entity.Percent;
import com.productivit.task.taskmanager.service.percent.PercentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("percent")
@AllArgsConstructor
public class PercentController {

    private PercentService percentService;

    @GetMapping("{chatId}")
    public Integer getCurrentPercent(@PathVariable("chatId") Long chatId) {
        return percentService.getCurrentPercent(chatId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setPercent(@RequestBody Percent percent) {
        percentService.setPercent(percent);
    }
}
