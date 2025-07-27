package com.hb.cda.api_rest_exam.mapper;

import com.hb.cda.api_rest_exam.dto.ExpenseDTO;
import com.hb.cda.api_rest_exam.dto.ExpenseShareDTO;
import com.hb.cda.api_rest_exam.entity.Expense;
import com.hb.cda.api_rest_exam.entity.ExpenseShare;
import com.hb.cda.api_rest_exam.entity.User;

public class ExpenseShareMapper {

    public static ExpenseShareDTO toExpenseShareDTO(ExpenseShare expenseShare) {
        if (expenseShare == null) return null;

        ExpenseShareDTO dto = new ExpenseShareDTO();

        dto.setId(expenseShare.getId());
        dto.setAmount(expenseShare.getAmount());
        dto.setExpenseId(expenseShare.getExpense().getId());
        dto.setUserId(expenseShare.getUser().getId());
        return dto;

    }

    public static ExpenseShare toExpenseShare(ExpenseShareDTO dto, User user, Expense expense) {
        if (dto == null) return null;
        ExpenseShare expenseShare = new ExpenseShare();
        expenseShare.setId(dto.getId());
        expenseShare.setAmount(dto.getAmount());
        expenseShare.setUser(user);
        expenseShare.setExpense(expense);

        return expenseShare;
    }
}
