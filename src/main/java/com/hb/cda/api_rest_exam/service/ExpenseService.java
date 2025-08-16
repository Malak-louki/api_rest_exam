package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.ExpenseDTO;
import com.hb.cda.api_rest_exam.dto.SplitExpenseRequestDTO;

import java.util.List;

public interface ExpenseService {
    ExpenseDTO create(ExpenseDTO dto);
    List<ExpenseDTO> findAll();
    ExpenseDTO getExpenseById(String id);
    List<ExpenseDTO> getExpensesByGroup(String groupId);
    List<ExpenseDTO> getExpenseByUser(String userId);
    void deleteExpenseById(String id);
    ExpenseDTO splitExpense(SplitExpenseRequestDTO dto);

}
