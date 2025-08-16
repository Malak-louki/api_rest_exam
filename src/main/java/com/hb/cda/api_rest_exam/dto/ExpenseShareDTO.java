package com.hb.cda.api_rest_exam.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseShareDTO {
        private String id;
        private BigDecimal amount;
        private String userId;
        private String expenseId;
    }
