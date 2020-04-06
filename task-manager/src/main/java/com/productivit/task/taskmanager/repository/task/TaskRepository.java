package com.productivit.task.taskmanager.repository.task;

import com.productivit.task.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query(value = "UPDATE plan SET status = 'DONE', done_points = :donePoints WHERE chat_id = :chatId " +
            "AND assigned_date = :assignedDate AND id: taskId", nativeQuery = true)
    void setTaskAsDone(@Param("donePoints") Integer donePoints,
                       @Param("chatId") Long chatId,
                       @Param("assignedDate") Date assignedDate);
}
