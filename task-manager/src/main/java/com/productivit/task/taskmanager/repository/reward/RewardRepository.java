package com.productivit.task.taskmanager.repository.reward;

import com.productivit.task.taskmanager.entity.Reward;
import com.productivit.task.taskmanager.projection.RewardDaysProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    @Query(value = "SELECT * FROM reward WHERE chat_id = :chatId and status = 'ACTIVE'", nativeQuery = true)
    Optional<Reward> findActiveReward(@Param("chatId") Long chatId);

    @Modifying
    @Query(value = "UPDATE reward SET status = 'ACTIVE' WHERE id = :rewardId AND chat_id = :chatId", nativeQuery = true)
    void setActiveReward(@Param("chatId") Long chatId, @Param("rewardId") Long rewardId);

    @Query(value = "SELECT * FROM reward WHERE chat_id = :chatId ORDER BY created_date LIMIT :limit OFFSET :offset",
            nativeQuery = true)
    List<Reward> getRewardsForPage(@Param("chatId") Long chatId,
                                   @Param("limit") Integer limit,
                                   @Param("offset") Integer offset);

    @Query("SELECT new com.productivit.task.taskmanager.projection.RewardDaysProjection(neededDays, doneDays) " +
            "FROM Reward WHERE chatId = :chatId AND status = 'ACTIVE'")
    RewardDaysProjection getDaysInfo(@Param("chatId") Long chatId);

    @Modifying
    @Query(value = "UPDATE reward SET done_days = done_days + 1 WHERE chat_id = :chatId AND status = 'ACTIVE'",
            nativeQuery = true)
    void incrementDoneDays(@Param("chatId") Long chatId);
}
