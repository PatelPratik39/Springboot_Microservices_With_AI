package com.fitness.userservice.userMapper;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;

public class UserMapper {
    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

//    public static User toUser(RegisterRequest request) {
//        return new User(
//                request.getEmail(),
//                request.getPassword(),
//                request.getFirstName(),
//                request.getLastName()
//        );
//    }
}