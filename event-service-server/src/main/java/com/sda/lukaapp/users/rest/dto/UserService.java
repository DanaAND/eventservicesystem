package com.sda.lukaapp.users.rest.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.lukaapp.exception.NotFoundException;

import com.sda.lukaapp.users.domain.User;
import com.sda.lukaapp.users.domain.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.sda.lukaapp.exception.NotFoundException.ErrorCode.LOCATION_NOT_FOUND;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ObjectMapper jacksonObjectMapper;

    @Autowired
    UserService(UserRepository userRepository, UserMapper userMapper, ObjectMapper jacksonObjectMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    // TODO: implement findAll
    public List<UserResponse> findAll() {
        logger.debug("finding all users");
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    // TODO: implement findById
    public UserResponse findById(long id) {
        logger.debug("finding user by id: {}", id);
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        LOCATION_NOT_FOUND, "user not found")));
    }

    // TODO: implement save
    public UserResponse save(CreateUserRequest createUserRequest) {
        logger.debug("saving user: {}", createUserRequest);
        // TODO: validate user details
        // TODO: convert user request (DTO) to user (ENTITY)
        User user = userMapper.toEntity(createUserRequest);
        // TODO: save user in db using repository
        User newUser = userRepository.save(user);
        // Hibernate: insert into user (id, email, password, username) values (null, ?, ?, ?)
        // TODO: convert back to response (DTO)
        return userMapper.toDto(newUser);
    }

    // TODO: implement update
    public UserResponse update(UpdateUserRequest request, long id) {
        logger.debug("updating user with id: {} and request body: {}", id, request);
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        LOCATION_NOT_FOUND, "user not found"));
        // TODO: validate user details
        userMapper.map(request, userToUpdate);
        // TODO: save user in db using repository
        User updatedUser = userRepository.save(userToUpdate);
        // Hibernate: insert into user (id, email, password, username) values (null, ?, ?, ?)
        // TODO: convert back to response (DTO)
        return userMapper.toDto(updatedUser);
    }

    // FIXME: implement partial update
    public UserResponse partialUpdate(Map<String, Object> updates, long id) {
        logger.debug("patching user with id: {} and request body: {}", id, updates);
        // De-serialise request body into a DTO
        // Run some sort of validation
        // Load entity being updated
        // Copy fields that change over to the entity with the help of a Model Mapper
        // Save
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(
                            LOCATION_NOT_FOUND, "user not found"));
            // Jackson deserializes and copies value to the already initialised DTO
            jacksonObjectMapper.readerForUpdating(user)
                    .readValue(jacksonObjectMapper.writeValueAsBytes(updates));
            // TODO: save user in db using repository
            User updatedUser = userRepository.save(user);
            // Hibernate: insert into user (id, email, password, username) values (null, ?, ?, ?)
            // TODO: convert user details (DTO) to user (ENTITY)
            return userMapper.toDto(updatedUser);
        } catch (Exception e) {
            logger.error("failed to update user", e);
        }
        return null;
    }

    // TODO: implement delete
    public void delete(long id) {
        logger.debug("deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    // TODO: implement findByEmail using filter
    public List<UserResponse> findByEmail(String email) {
        logger.debug("finding users by email: {}", email);
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}