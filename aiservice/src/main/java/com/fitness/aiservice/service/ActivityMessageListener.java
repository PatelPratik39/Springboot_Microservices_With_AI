package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAiService aiService;

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity) {
        log.info("Received activity for processing : {}", activity.getId());
        try {
            String recommendation = aiService.generateRecommendation(activity);
            log.info("Generated Recommendation: {}", recommendation);
        } catch (Exception e) {
            log.error("❌ Failed to generate recommendation: {}", e.getMessage(), e);
            // Optionally send to a dead-letter queue or alert system
        }
    }


}
