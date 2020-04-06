package com.productivit.task.taskmanager.service.task;

import com.productivit.task.taskmanager.dto.task.AddTaskDto;
import com.productivit.task.taskmanager.dto.task.MarkTaskAsDoneDto;
import com.productivit.task.taskmanager.entity.Task;
import com.productivit.task.taskmanager.enums.TaskStatus;
import com.productivit.task.taskmanager.repository.task.TaskRepository;
import com.productivit.task.taskmanager.service.plan.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TaskService {

    private static final Integer DEFAULT_DONE_POINTS = 0;

    private TaskRepository taskRepository;

    private PlanService planService;

    public void addTask(AddTaskDto addTaskDto) {

        Long assignedPlanId = planService
                .getIdByAssignedDateAndAndChatId(addTaskDto.getAssignedDate(), addTaskDto.getChatId());

        if (assignedPlanId == null) {
            assignedPlanId = planService.createPlan(addTaskDto.getAssignedDate(), addTaskDto.getChatId());
        }

        Task newTask = Task.builder()
                .description(addTaskDto.getDescription())
                .points(addTaskDto.getPoints())
                .createdTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .updatedTimestamp(Timestamp.valueOf(LocalDateTime.now()))
                .planId(assignedPlanId)
                .status(TaskStatus.CREATED)
                .donePoints(DEFAULT_DONE_POINTS)
                .build();

        taskRepository.save(newTask);
    }

    @Transactional
    public void markAsDone(MarkTaskAsDoneDto markTaskAsDoneDto) {
        taskRepository.setTaskAsDone(
                markTaskAsDoneDto.getDonePoints(),
                markTaskAsDoneDto.getChatId(),
                markTaskAsDoneDto.getAssignedDate());
    }
}
