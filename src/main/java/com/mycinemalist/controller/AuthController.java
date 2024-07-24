package com.mycinemalist.controller;

import com.mycinemalist.entity.User;
import com.mycinemalist.repository.UserRepository;
import com.mycinemalist.security.JwtRequest;
import com.mycinemalist.security.JwtResponse;
import com.mycinemalist.security.JwtTokenUtil;
import com.mycinemalist.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(jwtRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        // Fetch user to get the user ID
        User user = userRepository.findByEmail(jwtRequest.getEmail());

        return ResponseEntity.ok(new JwtResponse(token, user.getId().toHexString()));

    }


    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (Exception e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
