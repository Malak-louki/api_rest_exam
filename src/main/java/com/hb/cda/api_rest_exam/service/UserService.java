package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.ExpenseDTO;
import com.hb.cda.api_rest_exam.dto.GroupDTO;
import com.hb.cda.api_rest_exam.dto.UserCreateDTO;
import com.hb.cda.api_rest_exam.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(UserCreateDTO dto);
    List<UserDTO> findAll();
    UserDTO getById(String id);
    List<GroupDTO> getGroupsByUserId(String userId);
    List<ExpenseDTO> getExpensesByUserId(String userId);
    void delete(String id);

}
