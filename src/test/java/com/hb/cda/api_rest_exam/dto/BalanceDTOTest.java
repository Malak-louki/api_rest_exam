package com.hb.cda.api_rest_exam.dto;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class BalanceDTOTest {
    @Test
    void shouldCreateValidBalance() {
        BalanceDTO dto = new BalanceDTO("user1", "user2", new BigDecimal("10.50"));

        assertEquals("user1", dto.getDebtorId());
        assertEquals("user2", dto.getCreditorId());
        assertEquals(new BigDecimal("10.50"), dto.getAmount());
    }
}