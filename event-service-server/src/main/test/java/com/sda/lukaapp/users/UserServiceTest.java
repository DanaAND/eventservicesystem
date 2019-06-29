package com.sda.lukaapp.users;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.lukaapp.users.domain.User;
import com.sda.lukaapp.users.domain.UserRepository;
import com.sda.lukaapp.users.rest.dto.CreateUserRequest;
import com.sda.lukaapp.users.rest.dto.UserMapper;
import com.sda.lukaapp.users.rest.dto.UserResponse;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private ObjectMapper jacksonObjectMapper;

    @InjectMocks
    private UserService userService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public UserServiceTest() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void givenUser_whenFindById_thenReturnUser() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("testusername");
        createUserRequest.setPassword("testpassword");
        createUserRequest.setEmail("test@test.com");

        when(userRepository.save(any(User.class))).thenReturn(new User());

        UserResponse created = userService.save(createUserRequest);

        assertThat(created.getUsername()).isSameAs(createUserRequest.getUsername());
    }

    @Test(expected = NullPointerException.class)
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void partialUpdate() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findByEmail() {
    }
}
