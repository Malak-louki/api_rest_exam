package com.hb.cda.api_rest_exam.mapper;

import com.hb.cda.api_rest_exam.dto.UserCreateDTO;
import com.hb.cda.api_rest_exam.dto.UserDTO;
import com.hb.cda.api_rest_exam.entity.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        if(user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public static UserCreateDTO toUserCreateDTO(User user) {
        if(user == null) return null;
        UserCreateDTO createDTO = new UserCreateDTO();
        createDTO.setEmail(user.getEmail());
        createDTO.setUsername(user.getUsername());
        createDTO.setPassword(user.getPassword());
        return createDTO;
    }

    public static User toUser(UserCreateDTO dto) {
        if(dto == null)return null;
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }

}
