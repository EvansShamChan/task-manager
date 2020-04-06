package com.productivit.task.taskmanager.controller.plan;

import com.productivit.task.taskmanager.dto.plan.CreatePlanDto;
import com.productivit.task.taskmanager.dto.plan.PlanDto;
import com.productivit.task.taskmanager.service.plan.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("plan")
@AllArgsConstructor
public class PlanController {

    private PlanService planService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPlan(@RequestBody CreatePlanDto createPlanDto) {
        planService.createPlan(createPlanDto.getAssignedDate(), createPlanDto.getChatId());
    }

    @GetMapping("{assignedDate}/chat/{chatId}")
    public PlanDto getPlan(@PathVariable("assignedDate") Date assignedDate,
                           @PathVariable("chatId") Long chatId) {
        return planService.getPlan(assignedDate, chatId);
    }
}
