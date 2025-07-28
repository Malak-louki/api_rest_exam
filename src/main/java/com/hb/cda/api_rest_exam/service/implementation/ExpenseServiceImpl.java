package com.hb.cda.api_rest_exam.service.implementation;

import com.hb.cda.api_rest_exam.dto.ExpenseDTO;
import com.hb.cda.api_rest_exam.entity.Expense;
import com.hb.cda.api_rest_exam.mapper.ExpenseMapper;
import com.hb.cda.api_rest_exam.repository.ExpenseRepository;
import com.hb.cda.api_rest_exam.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Override
    public ExpenseDTO create(ExpenseDTO dto) {
        Expense expense = ExpenseMapper.toExpense(dto);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.toExpenseDTO(savedExpense);
    }

    @Override
    public List<ExpenseDTO> findAll() {

        return expenseRepository.findAll().stream()
                .map(ExpenseMapper::toExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDTO getExpenseById(String id) {
        return expenseRepository.findById(id)
                .map(ExpenseMapper::toExpenseDTO)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public List<ExpenseDTO> getExpenseByUser(String userId) {
        return expenseRepository.findByUserId(userId).stream()
                .map(ExpenseMapper::toExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDTO> getExpenseByGroup(String groupId) {
        return expenseRepository.findByGroupId(groupId).stream()
                .map(ExpenseMapper::toExpenseDTO)
                .collect(Collectors.toList());
    }
}
