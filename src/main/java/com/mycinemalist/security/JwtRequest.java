package com.mycinemalist.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private String email;
    private String password;

}
