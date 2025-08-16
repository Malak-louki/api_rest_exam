package com.hb.cda.api_rest_exam.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SplitExpenseRequestDTO {
    private String groupId;
    private BigDecimal montantTotal;
    private String description;
    private String payerId;
}