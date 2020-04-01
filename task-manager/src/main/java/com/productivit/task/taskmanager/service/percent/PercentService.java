package com.productivit.task.taskmanager.service.percent;

import com.productivit.task.taskmanager.entity.Percent;
import com.productivit.task.taskmanager.repository.percent.PercentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PercentService {

    private static final Integer DEFAULT_PERCENT = 70;

    private PercentRepository percentRepository;

    public Integer getCurrentPercent(Long chatId) {
        Integer currentPercent = percentRepository.getCurrentPercent(chatId);
        if (currentPercent == null) {
            percentRepository.save(
                    Percent.builder()
                            .chatId(chatId)
                            .percent(DEFAULT_PERCENT)
                            .build()
            );
        }
        return currentPercent;
    }

    @Transactional
    public void setPercent(Percent percent) {
        Integer currentPercent = percentRepository.getCurrentPercent(percent.getChatId());

        if (currentPercent == null) {
            percentRepository.save(percent);
        } else {
            percentRepository.setPercent(percent.getChatId(), percent.getPercent());
        }
    }
}
