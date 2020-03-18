package com.productivit.task.taskmanager.repository.reward;

import com.productivit.task.taskmanager.entity.Reward;
import com.productivit.task.taskmanager.enums.RewardStatus;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

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

    private static final String GET_ACTIVE_REWARD = "SELECT * FROM reward WHERE chat_id = ? AND status = 'ACTIVE'";

    private static final String GET_REWARDS = "SELECT * FROM reward WHERE chat_id = ?";

    private static final String SET_ACTIVE_STATUS = "UPDATE reward SET status = ? where id = ?";

    private static final String DISABLE_ACTIVE_REWARD =
            "UPDATE reward SET status = 'CREATED' WHERE chat_id = ? AND status = 'ACTIVE'";

    private static final RowMapper<Reward> GET_REWARD_MAPPER = ((rs, rowNum) -> Reward.builder()
            .id(rs.getLong("id"))
            .description(rs.getString("description"))
            .neededDays(rs.getInt("needed_days"))
            .status(RewardStatus.valueOf(rs.getString("status")))
            .chatId(rs.getLong("chat_id")).build());

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

    public Reward getActiveReward(Long chatId) {
        try {
            return jdbcTemplate.queryForObject(GET_ACTIVE_REWARD, new Object[]{chatId}, GET_REWARD_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("No active reward for chatId %s was found.", chatId));
        }
    }

    public List<Reward> getAllRewards(Long chatId) {
        return jdbcTemplate.query(GET_REWARDS, new Object[]{chatId}, GET_REWARD_MAPPER);
    }

    public void setRewardStatus(Long rewardId, RewardStatus status) {
        jdbcTemplate.update(SET_ACTIVE_STATUS, status.toString(), rewardId);
    }

    public void disableActiveReward(Long chatId) {
        jdbcTemplate.update(DISABLE_ACTIVE_REWARD, chatId);
    }
}
