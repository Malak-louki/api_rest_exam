package com.hb.cda.api_rest_exam.dto;

import lombok.Data;

@Data
public class UserCreateDTO {

    private String email;
    private String role;
    private String username;

}
