package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import org.springframework.stereotype.Service;

@Service
public interface ActivityService {
    /**
     * Tracks a user's activity.
     *
     * @param activityRequest the activity request containing user and activity details
     * @return the response containing the tracked activity details
     */
    ActivityResponse trackActivity(ActivityRequest activityRequest);
}