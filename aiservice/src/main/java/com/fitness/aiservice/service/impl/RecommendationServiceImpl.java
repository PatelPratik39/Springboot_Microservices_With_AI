package com.fitness.aiservice.service.impl;

import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repository.RecommendationRepository;
import com.fitness.aiservice.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    @Override
    public List<Recommendation> getUserRecommendations(String userId) {
        return recommendationRepository.findByUserId(userId);
    }

    @Override
    public Recommendation getActivityRecommendations(String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow( () -> new RuntimeException("No recommendations found for activity ID: " + activityId));
    }
}