package com.hb.cda.api_rest_exam.service.implementation;

import com.hb.cda.api_rest_exam.dto.ExpenseShareDTO;

import com.hb.cda.api_rest_exam.entity.Expense;
import com.hb.cda.api_rest_exam.entity.ExpenseShare;

import com.hb.cda.api_rest_exam.entity.User;
import com.hb.cda.api_rest_exam.exception.ResourceNotFoundException;
import com.hb.cda.api_rest_exam.mapper.ExpenseShareMapper;
import com.hb.cda.api_rest_exam.repository.ExpenseRepository;
import com.hb.cda.api_rest_exam.repository.ExpenseShareRepository;
import com.hb.cda.api_rest_exam.repository.UserRepository;
import com.hb.cda.api_rest_exam.service.ExpenseShareService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseShareServiceImpl implements ExpenseShareService {

    private final ExpenseShareRepository expenseShareRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    @Override
    public ExpenseShareDTO getById(String id) {
        return expenseShareRepository.findById(id)
                .map(ExpenseShareMapper::toExpenseShareDTO)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    @Override
    public List<ExpenseShareDTO> listByExpense(String expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(()-> new RuntimeException("expense not found"));
        return  expense.getShares()
                .stream()
                .map(ExpenseShareMapper::toExpenseShareDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseShareDTO createExpenseShare(ExpenseShareDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Expense expense = expenseRepository.findById(dto.getExpenseId())
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        ExpenseShare expenseShare = ExpenseShareMapper.toExpenseShare(dto, user, expense);
        ExpenseShare savedExpenseShare = expenseShareRepository.save(expenseShare);
        return ExpenseShareMapper.toExpenseShareDTO(savedExpenseShare);
    }

    @Override
    public List<ExpenseShareDTO> getSharesByExpense(String expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        return expense.getShares().stream()
                .map(ExpenseShareMapper::toExpenseShareDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void markAsPaid(String shareId) {
        ExpenseShare share = expenseShareRepository.findById(shareId)
                .orElseThrow(() -> new ResourceNotFoundException("Share not found"));

        share.setIsPaid(true);
        expenseShareRepository.save(share);
    }
}
