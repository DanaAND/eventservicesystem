package com.sda.lukaapp.users.rest;


import com.sda.lukaapp.users.UserService;
import com.sda.lukaapp.users.rest.dto.CreateUserRequest;
import com.sda.lukaapp.users.rest.dto.UpdateUserRequest;
import com.sda.lukaapp.users.rest.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// TODO: implement controller using request mapping
@RestController
@RequestMapping(UserController.API_USERS)
public class UserController {

    public static final String API_USERS = "/api/users";

    private UserService userService;

    // TODO: inject user service in constructor
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO: implement find all using GET /api/users
    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    // TODO: implement find by id using GET /api/users/id
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable("id") final long id) {
        return userService.findById(id);
    }

    // TODO: implement create using POST /api/users
    @PostMapping
    public UserResponse create(@RequestBody final CreateUserRequest createAccountRequest) {
        return userService.save(createAccountRequest);
    }

    // TODO: implement update using PUT /api/users/id
    @PutMapping("/{id}")
    public UserResponse update(@RequestBody final UpdateUserRequest updateUserRequest,
                               @PathVariable("id") final long id) {
        return userService.update(updateUserRequest, id);
    }

    // TODO: implement partial update using PATCH /api/users/id
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponse patch(@RequestBody final Map<String, Object> updates,
                              @PathVariable("id") final long id) {
        return userService.partialUpdate(updates, id);
    }

    // TODO: implement delete using DELETE /api/users/id
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") final long id) {
        userService.delete(id);
    }
}
