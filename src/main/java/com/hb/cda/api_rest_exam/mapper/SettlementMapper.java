package com.hb.cda.api_rest_exam.mapper;

import com.hb.cda.api_rest_exam.dto.SettlementDTO;
import com.hb.cda.api_rest_exam.entity.Settlement;
import com.hb.cda.api_rest_exam.entity.User;

public class SettlementMapper {

    public static SettlementDTO toSettlementDTO(Settlement settlement) {
        if (settlement == null) return null;

        SettlementDTO dto = new SettlementDTO();
        dto.setId(settlement.getId());
        dto.setAmount(settlement.getAmount());
        dto.setPaymentDate(settlement.getPaymentDate());

        dto.setGroupId(settlement.getGroup()==null?null:settlement.getGroup().getId());
        dto.setFromUserId(settlement.getFromUser()==null?null:settlement.getFromUser().getId());
        dto.setToUserId(settlement.getToUser()==null?null:settlement.getToUser().getId());

        return dto;

    }

    public static Settlement toSettlement(SettlementDTO dto, User fromUser, User toUser) {
        if (dto == null) return null;
        Settlement settlement = new Settlement();
        settlement.setId(dto.getId());
        settlement.setAmount(dto.getAmount());
        settlement.setPaymentDate(dto.getPaymentDate());
        settlement.setToUser(toUser);
        settlement.setFromUser(fromUser);
        return settlement;

    }
}
