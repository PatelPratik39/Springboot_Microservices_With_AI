package com.fitness.activityservice.service.impl;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repository.ActivityRepository;
import com.fitness.activityservice.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityRequestImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Override
    public ActivityResponse trackActivity(ActivityRequest activityRequest) {

        Activity activity =  Activity.builder()
                .userId(activityRequest.getUserId())
                .type(activityRequest.getType())
                .duration(activityRequest.getDuration())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .startTime(activityRequest.getStartTime())
                .additionalMetrics(activityRequest.getAdditionalMetrics())
                .build();
        Activity savedActivity = activityRepository.save(activity);
        return mapToResponse(savedActivity);
        }

    private ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
         response.setId(activity.getId());
         response.setUserId(activity.getUserId());
         response.setType(activity.getType());
         response.setDuration(activity.getDuration());
         response.setCaloriesBurned(activity.getCaloriesBurned());
         response.setStartTime(activity.getStartTime());
         response.setAdditionalMetrics(activity.getAdditionalMetrics());
         return response;
    }
}