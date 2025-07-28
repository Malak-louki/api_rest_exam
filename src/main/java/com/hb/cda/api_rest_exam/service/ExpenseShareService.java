package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.ExpenseShareDTO;
import com.hb.cda.api_rest_exam.entity.ExpenseShare;

import java.util.List;

public interface ExpenseShareService {
    ExpenseShareDTO getById(String id);
    List<ExpenseShareDTO> listByExpense(String expenseId);

}
