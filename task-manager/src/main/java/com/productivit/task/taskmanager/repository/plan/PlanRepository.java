package com.productivit.task.taskmanager.repository.plan;

import com.productivit.task.taskmanager.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    @Query(value = "SELECT id FROM plan WHERE chat_id = :chatId AND assigned_date = :assignedDate",
            nativeQuery = true)
    Long getIdByAssignedDateAndAndChatId(@Param("assignedDate") Date assignedDate,
                                            @Param("assignedDate") Long chatId);
}
