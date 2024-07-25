package com.mycinemalist.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;
    private String email;
    private String displayName;
}
