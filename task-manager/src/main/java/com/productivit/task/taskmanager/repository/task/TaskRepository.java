package com.productivit.task.taskmanager.repository.task;

import com.productivit.task.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Query(value = "UPDATE task SET status = 'DONE', done_points = :donePoints WHERE id = :taskId",
            nativeQuery = true)
    void setTaskAsDone(@Param("donePoints") Integer donePoints,
                       @Param("taskId") Long taskId);

    @Query(value = "SELECT * FROM task WHERE plan_id = :planId", nativeQuery = true)
    List<Task> getPlanTasks(@Param("planId") Long planId);
}
