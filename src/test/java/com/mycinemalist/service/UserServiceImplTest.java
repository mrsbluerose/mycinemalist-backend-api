package com.mycinemalist.service;

import com.mycinemalist.entity.User;
import com.mycinemalist.repository.UserRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private String rawPassword = "password";
    private String encodedPassword = "encodedPassword";

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(new ObjectId());
        user.setEmail("test@example.com");
        user.setPassword(rawPassword);
    }

    @Test
    public void testCreateUser() {
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertEquals(user.getEmail(), createdUser.getEmail());
        assertEquals(encodedPassword, createdUser.getPassword());
        verify(passwordEncoder, times(1)).encode(rawPassword);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testFindByUserId() {
        ObjectId userId = user.getId();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findByUserId(userId.toHexString());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        verify(userRepository, times(1)).findById(userId);
    }
}
