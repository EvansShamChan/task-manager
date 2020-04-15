package com.productivit.task.taskmanager.repository.plan;

import com.productivit.task.taskmanager.entity.Plan;
import com.productivit.task.taskmanager.projection.PlanStatusIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query(value = "SELECT id, status FROM plan WHERE chat_id = :chatId AND assigned_date = :assignedDate",
            nativeQuery = true)
    PlanStatusIdProjection getIdByAssignedDateAndAndChatId(@Param("assignedDate") Date assignedDate,
                                                           @Param("chatId") Long chatId);

    @Modifying
    @Query(value = "UPDATE plan SET status = 'FINISHED' WHERE chat_id = :chatId AND assigned_date = :assignedDate",
            nativeQuery = true)
    void markPlanAsFinished(@Param("assignedDate") Date assignedDate, @Param("chatId") Long chatId);
}
