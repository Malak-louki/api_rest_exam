package com.hb.cda.api_rest_exam.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String email;
    private String password;
    private String role;
}
