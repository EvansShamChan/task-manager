package com.productivit.task.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@Table(name = "percent")
@NoArgsConstructor
@AllArgsConstructor
public class Percent {

    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "percent")
    private Integer percent;
}
