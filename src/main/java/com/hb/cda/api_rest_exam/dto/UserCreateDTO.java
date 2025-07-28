package com.hb.cda.api_rest_exam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDTO {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min=4, max=64)
    private String password;

}
