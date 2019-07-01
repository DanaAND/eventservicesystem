package com.sda.lukaapp.users.rest.dto;

import com.sda.lukaapp.users.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // dto to entity conversions

    public User toEntity(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    // entity to dto conversions

    public List<UserResponse> toDto(List<User> users) {
        return users.stream()
                .map(user -> this.toDto(user))
                .collect(Collectors.toList());
    }

    public List<UserResponse> toDtoOldWay(List<User> givenList) {
        // create new list for modified objects
        List<UserResponse> resultList = new ArrayList<>();
        // for each item in given list
        for (User user : givenList) {
            // transform to dto (by copying all fields)
            UserResponse userResponse = this.toDto(user);
            // add transformed item to the new list
            resultList.add(userResponse);
        }
        return resultList;
    }

    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        return userResponse;
    }

    // mapping used for partial update

    public void map(UpdateUserRequest request, User user) {
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }
    }
}