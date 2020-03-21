package com.productivit.task.taskmanager.repository.reward;

import com.productivit.task.taskmanager.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    @Query(value = "SELECT * FROM reward WHERE chat_id = :chatId and status = 'ACTIVE'", nativeQuery = true)
    Reward findActiveReward(@Param("chatId") Long chatId);

    @Query(value = "UPDATE reward SET status = 'ACTIVE' WHERE id = :rewardId AND chat_id = :chatId", nativeQuery = true)
    void setActiveReward(@Param("chatId") Long chatId, @Param("rewardId") Long rewardId);
}
