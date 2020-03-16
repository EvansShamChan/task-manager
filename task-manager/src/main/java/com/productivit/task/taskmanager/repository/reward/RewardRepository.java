package com.productivit.task.taskmanager.repository.reward;

import com.productivit.task.taskmanager.entity.Reward;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class RewardRepository {

    private static final String ADD_REWARD = "INSERT INTO reward(" +
            "description, " +
            "needed_days, " +
            "status, " +
            "created_date, " +
            "updated_date, " +
            "chat_id) values (?, ?, ?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    public void addNewReward(Reward reward) {
        jdbcTemplate.update(ADD_REWARD,
                reward.getDescription(),
                reward.getNeededDays(),
                reward.getStatus().toString(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                reward.getChatId());
    }
}
