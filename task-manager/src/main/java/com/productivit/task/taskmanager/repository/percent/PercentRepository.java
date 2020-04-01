package com.productivit.task.taskmanager.repository.percent;

import com.productivit.task.taskmanager.entity.Percent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PercentRepository extends JpaRepository<Percent, Long> {

    @Query(value = "SELECT percent FROM percent WHERE chat_id = :chatId", nativeQuery = true)
    Integer getCurrentPercent(@Param("chatId") Long chatId);

    @Modifying
    @Query(value = "UPDATE percent SET percent = :newPercent WHERE chat_id = :chatId", nativeQuery = true)
    void setPercent(@Param("chatId") Long chatId, @Param("newPercent") Integer percent);
}
