package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.ExpenseShareDTO;

import java.util.List;

public interface ExpenseShareService {
    ExpenseShareDTO getById(String id);
    List<ExpenseShareDTO> listByExpense(String expenseId);
    ExpenseShareDTO createExpenseShare(ExpenseShareDTO dto);
    List<ExpenseShareDTO> getSharesByExpense(String expenseId);
    void markAsPaid(String shareId);

}
