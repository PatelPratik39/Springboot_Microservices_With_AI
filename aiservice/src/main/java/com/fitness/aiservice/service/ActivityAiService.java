package com.fitness.aiservice.service;

import com.fitness.aiservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityAiService {

    private final GeminiService geminiService;

    public String generateRecommendation(Activity activity) {
        String prompt = createPromptForActivity(activity);
        String aiResponse = geminiService.getAnswer(prompt);
        log.info("Gemini AI Response: {} " , aiResponse);
        return aiResponse;
    }

    private String createPromptForActivity(Activity activity) {
        return String.format(
                """
                        Analyze this fitness activity and provide detailed recommendations in the following format
                        {
                            "analysis": {
                                "overall" : "Overall analysis here",
                                "pace" : "Pace analysis here",
                                "heartRate" : "HeartRate analysis here",
                                "caloriesBurned" : "CaloriesBurned analysis here"
                        },
                        "improvements": [
                         {
                            "area" : "Area name",
                            "recommendations": "Detailed recommendations",
                          }
                        ],
                        "suggestions": [
                        {
                            "workout" : "Workout name",
                            "description" : "Workout description",
                        }
                       
                        ],
                        "safety" : [
                            "Safety point 1",
                            "Safety point 2",
                            ]
                        }
                        Analyze this activity:
                        Activity Type: %s
                        Duration: %d minutes
                        CaloriesBurned: %d 
                        Additional Metrics : %s
                        
                        Provide detailed analysis focusing on performance, improvements, next workout suggestions and safet guidelines.
                        Ensure the response follows the EXACT JSON format show above.
                        """,
                activity.getType(),
                activity.getDuration(),
                activity.getCaloriesBurned(),
                activity.getAdditionalMetrics()

        );
    }

}
