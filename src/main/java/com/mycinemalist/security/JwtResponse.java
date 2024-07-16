package com.mycinemalist.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private final String token;
    private final String username;
}
