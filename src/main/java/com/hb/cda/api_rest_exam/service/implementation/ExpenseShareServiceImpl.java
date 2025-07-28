package com.hb.cda.api_rest_exam.service.implementation;

import com.hb.cda.api_rest_exam.dto.ExpenseShareDTO;
import com.hb.cda.api_rest_exam.service.ExpenseShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseShareServiceImpl implements ExpenseShareService {
    @Override
    public ExpenseShareDTO getById(String id) {
        return null;
    }

    @Override
    public List<ExpenseShareDTO> listByExpense(String expenseId) {
        return List.of();
    }
}
