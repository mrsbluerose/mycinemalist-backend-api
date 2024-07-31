package com.mycinemalist.controller;

import com.mycinemalist.entity.User;
import com.mycinemalist.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        user.setDisplayName("Test User");

        User savedUser = new User();
        savedUser.setEmail("testuser@example.com");
        savedUser.setPassword("encodedPassword");
        savedUser.setDisplayName("Test User");

        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userService.createUser(any(User.class))).thenReturn(savedUser);

        User result = userController.registerUser(user);

        assertEquals(savedUser, result);
    }

    @Test
    public void testGetUser() {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setDisplayName("Test User");

        when(userService.findByUserId("1")).thenReturn(Optional.of(user));

        User result = userController.getUser("1");

        assertEquals(user, result);
    }

    @Test
    public void testGetUserNotFound() {
        when(userService.findByUserId("1")).thenReturn(Optional.empty());

        User result = userController.getUser("1");

        assertNull(result);
    }
}
