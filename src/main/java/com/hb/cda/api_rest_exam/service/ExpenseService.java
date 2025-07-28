package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.ExpenseDTO;

import java.util.List;

public interface ExpenseService {
    ExpenseDTO create(ExpenseDTO dto);
    List<ExpenseDTO> findAll();
    ExpenseDTO getExpenseById(String id);
    List<ExpenseDTO> getExpenseByUser(String userId);
    List<ExpenseDTO> getExpenseByGroup(String groupId);

}
