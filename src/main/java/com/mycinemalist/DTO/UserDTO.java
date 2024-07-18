package com.mycinemalist.DTO;

import lombok.Builder;

@Builder
public class UserDTO {
    private String id;
    private String email;
    private String displayName;
}
