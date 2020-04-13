package com.productivit.task.taskmanager.mapper;

import com.productivit.task.taskmanager.dto.task.TaskDto;
import com.productivit.task.taskmanager.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskDtoMapper {

    public static List<TaskDto> mapTasks(List<Task> tasks) {
        return tasks.stream().map(task ->
                TaskDto.builder()
                        .id(task.getId())
                        .description(task.getDescription())
                        .points(task.getPoints())
                        .donePoints(task.getDonePoints()).build())
                .collect(Collectors.toList());
    }
}
