package com.hb.cda.api_rest_exam.mapper;

import com.hb.cda.api_rest_exam.dto.ExpenseDTO;
import com.hb.cda.api_rest_exam.entity.Expense;
import com.hb.cda.api_rest_exam.entity.ExpenseShare;

import java.util.stream.Collectors;

public class ExpenseMapper {

    public static ExpenseDTO toExpenseDTO(Expense expense) {
        if(expense == null) return null;

        ExpenseDTO dto = new ExpenseDTO();
        dto.setId(expense.getId());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDate(expense.getExpenseDate());
        dto.setLabel(expense.getLabel());
        dto.setGroupId(expense.getGroup()==null?null:expense.getGroup().getId());
        dto.setUserId(expense.getUser()==null?null:expense.getUser().getId());
        dto.setExpenseShareIds(expense.getShares().stream()
                .map(ExpenseShare::getId)
                .collect(Collectors.toSet()));
        return dto;
    }
    public static Expense toExpense(ExpenseDTO dto) {
        if(dto == null) return null;
        Expense expense = new Expense();
        expense.setId(dto.getId());
        expense.setAmount(dto.getAmount());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setLabel(dto.getLabel());
        return expense;
    }
}
