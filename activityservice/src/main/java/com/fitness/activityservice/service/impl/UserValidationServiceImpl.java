package com.fitness.activityservice.service.impl;

import com.fitness.activityservice.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserValidationServiceImpl implements UserValidationService {

    private final WebClient userServiceWebClient;

    public boolean validateUser(String userId) {
        log.info("Calling User Validation API if user exists with id: {}", userId);
        try{
            Boolean response = userServiceWebClient
                    .get()
                    .uri("/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (response == null) {
                throw new RuntimeException("Validation response was null for userId: " + userId);
            }
            return response;

        } catch (WebClientResponseException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new RuntimeException("User not found : " + userId);
            } else if(e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("Invalid Request Id :  " + userId);
            }
        }
        return false;
    }
}