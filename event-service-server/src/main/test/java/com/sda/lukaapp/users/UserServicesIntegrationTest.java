package com.sda.lukaapp.users;

import com.sda.lukaapp.users.rest.dto.CreateUserRequest;
import com.sda.lukaapp.users.rest.dto.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class UserServicesIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void givenUser_whenFindAll_thenReturnAListWithOneUser() {
        // given
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("alex90");
        createUserRequest.setEmail("alex@gmail.com");
        createUserRequest.setPassword("secret");

        // when
        userService.save(createUserRequest);

        List<UserResponse> expectedUsers = userService.findAll();

        // then
        assertThat(expectedUsers.size()).isEqualTo(1);
    }
}