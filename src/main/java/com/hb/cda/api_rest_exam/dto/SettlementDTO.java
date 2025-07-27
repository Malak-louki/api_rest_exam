package com.hb.cda.api_rest_exam.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SettlementDTO {
    private String id;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String fromUserId;
    private String toUserId;
    private String groupId;
}