package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Recommendation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationService {
    List<Recommendation> getUserRecommendations(String userId);

    List<Recommendation> getActivityRecommendations(String activityId);
}