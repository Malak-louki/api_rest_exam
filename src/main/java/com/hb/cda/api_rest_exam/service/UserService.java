package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.UserCreateDTO;
import com.hb.cda.api_rest_exam.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserCreateDTO dto);
    List<UserDTO> findAll();
    UserDTO getById(String id);
    void delete(String id);

}
