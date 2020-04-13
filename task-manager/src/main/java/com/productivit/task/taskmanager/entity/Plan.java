package com.productivit.task.taskmanager.entity;

import com.productivit.task.taskmanager.enums.PlanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "plan")
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "assigned_date")
    private Date assignedDate;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "created_date", nullable = false)
    private Timestamp createdTimestamp;

    @Column(name = "updated_date", nullable = false)
    private Timestamp updatedTimestamp;

    @Column(name = "reward_id")
    private Long rewardId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PlanStatus status;
}
