package com.hb.cda.api_rest_exam.dto;


import lombok.Data;
import java.math.BigDecimal;

@Data
public class BalanceDTO {
    private String debtorId;    // ID de la personne qui doit
    private String creditorId; // ID de la personne à qui on doit
    private BigDecimal amount; // Montant dû

    public BalanceDTO(String debtorId, String creditorId, BigDecimal amount) {
        this.debtorId = debtorId;
        this.creditorId = creditorId;
        this.amount = amount;
    }
}