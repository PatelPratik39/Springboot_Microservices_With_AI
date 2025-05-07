package com.fitness.userservice.controller;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
//    Register a User
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register( @Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

//    GET User Profile
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        UserResponse response = userService.getUserProfile(userId);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();  // return 404 if user not found
    }

//    Validate User
    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.existByUserId(userId));
    }

//    Update User Profile
     @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUserProfile(@PathVariable String userId, @Valid @RequestBody RegisterRequest request){
        UserResponse response = userService.updateUserProfile(userId, request);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();  // return 404 if user not found
     }

//     Delete User Profile
     @DeleteMapping("/{userId}")
     public ResponseEntity<Void> deleteUserProfile(@PathVariable String userId){
         userService.deleteUserProfile(userId);
         return ResponseEntity.noContent().build();  // return 204 No Content
     }
}