package com.hb.cda.api_rest_exam.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class ExpenseDTO {
    private String id;
    private String label;
    private BigDecimal amount;
    private LocalDate expenseDate;
    private String  userId;
    private String groupId;
    private Set<String> expenseShareIds;

}
