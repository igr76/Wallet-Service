package com.igr.walletservice.service;

import com.igr.walletservice.entity.User;
import com.igr.walletservice.repository.UserRepository;
import com.igr.walletservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@Testcontainers
class UserServiceTest {
    UserServiceImpl userService;
    UserRepository userRepository;
    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer= new PostgreSQLContainer<>
            ("postgres:14")
            .withDatabaseName("x")
            .withUsername("igr")
            .withPassword("111111")
            .withInitScript("changelog.xml");

    @Test
    void greateUser() {
        User user= new User();
        user.setLogin("user11");
        user.setPassword("1234");
        assertThat(userService.greateUser(user)).isEqualTo(true);
        verify(userRepository, times(2));

    }

    @Test
    void updateUser() {
        User user= new User();
        user.setLogin("user1");
        user.setPassword("5555");
        assertThat(userService.updateUser(user)).isEqualTo(true);
        verify(userRepository, times(2));
    }

    @Test
    void deleteUser() {
        User user= new User();
        user.setLogin("user11");
        user.setPassword("1234");
        assertThat(userService.deleteUser(user)).isEqualTo(true);
        verify(userRepository, times(2));
    }

    @Test
    void checkPassword() {
        User user= new User();
        user.setLogin("user2");
        user.setPassword("222222");
        assertThat(userService.checkPassword(user)).isEqualTo(true);
        verify(userRepository, times(1));
    }
}