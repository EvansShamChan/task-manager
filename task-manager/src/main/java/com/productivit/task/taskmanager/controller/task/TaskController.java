package com.productivit.task.taskmanager.controller.task;

import com.productivit.task.taskmanager.dto.task.AddTaskDto;
import com.productivit.task.taskmanager.dto.task.MarkTaskAsDoneDto;
import com.productivit.task.taskmanager.service.task.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("task")
@AllArgsConstructor
public class TaskController {

    private TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@RequestBody AddTaskDto addTaskDto) {
        taskService.addTask(addTaskDto);
    }

    @PutMapping
    public void markAsDone(@RequestBody MarkTaskAsDoneDto markTaskAsDoneDto) {
        taskService.markAsDone(markTaskAsDoneDto);
    }
}
