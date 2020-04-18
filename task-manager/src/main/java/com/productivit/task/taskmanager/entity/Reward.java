package com.productivit.task.taskmanager.entity;

import com.productivit.task.taskmanager.enums.RewardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@Table(name = "reward")
@NoArgsConstructor
@AllArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "needed_days")
    private Integer neededDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RewardStatus status;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdTimestamp;

    @Column(name = "updated_date", nullable = false)
    private Timestamp updatedTimestamp;

    @Column(name = "chat_id", nullable = false)
    private Long chatId;

    @Column(name = "done_days")
    private Integer doneDays;
}
