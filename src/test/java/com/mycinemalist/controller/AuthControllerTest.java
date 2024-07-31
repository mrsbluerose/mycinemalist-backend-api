package com.mycinemalist.controller;

import com.mycinemalist.entity.User;
import com.mycinemalist.repository.UserRepository;
import com.mycinemalist.security.JwtRequest;
import com.mycinemalist.security.JwtResponse;
import com.mycinemalist.security.JwtTokenUtil;
import com.mycinemalist.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    private JwtRequest jwtRequest;
    private User user;
    private UserDetails userDetails;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        jwtRequest = new JwtRequest();
        jwtRequest.setEmail("test@example.com");
        jwtRequest.setPassword("password");

        user = new User();
        user.setId(new org.bson.types.ObjectId("60d5ec49f2e0a70d1c8e4b56"));
        user.setEmail("test@example.com");

        userDetails = org.springframework.security.core.userdetails.User
                .withUsername("test@example.com")
                .password("password")
                .authorities("USER")
                .build();

        jwtToken = "dummy-jwt-token";
    }

    @Test
    void testCreateAuthenticationToken() throws Exception {
        // Mock the authenticate method
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);

        // Mock the loadUserByUsername method
        when(userDetailsService.loadUserByUsername(eq(jwtRequest.getEmail()))).thenReturn(userDetails);

        // Mock the generateToken method
        when(jwtTokenUtil.generateToken(eq(userDetails))).thenReturn(jwtToken);

        // Mock the findByEmail method
        when(userRepository.findByEmail(eq(jwtRequest.getEmail()))).thenReturn(user);

        // Call the createAuthenticationToken method
        ResponseEntity<?> responseEntity = authController.createAuthenticationToken(jwtRequest);

        // Verify the response
        JwtResponse jwtResponse = (JwtResponse) responseEntity.getBody();
        assertEquals(jwtToken, jwtResponse.getToken());
        assertEquals(user.getId().toHexString(), jwtResponse.getUserId());

        // Verify the interactions
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsService, times(1)).loadUserByUsername(eq(jwtRequest.getEmail()));
        verify(jwtTokenUtil, times(1)).generateToken(eq(userDetails));
        verify(userRepository, times(1)).findByEmail(eq(jwtRequest.getEmail()));
    }

    @Test
    void testCreateAuthenticationTokenWithInvalidCredentials() {
        // Mock the authenticate method to throw an exception
        doThrow(new RuntimeException("INVALID_CREDENTIALS")).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Call the createAuthenticationToken method and expect an exception
        Exception exception = null;
        try {
            authController.createAuthenticationToken(jwtRequest);
        } catch (Exception e) {
            exception = e;
        }

        // Verify the exception
        assertEquals("INVALID_CREDENTIALS", exception.getMessage());

        // Verify the interactions
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsService, times(0)).loadUserByUsername(anyString());
        verify(jwtTokenUtil, times(0)).generateToken(any(UserDetails.class));
        verify(userRepository, times(0)).findByEmail(anyString());
    }
}
