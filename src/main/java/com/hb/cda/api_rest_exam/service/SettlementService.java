package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.SettlementDTO;
import com.hb.cda.api_rest_exam.entity.Settlement;

import java.util.List;

public interface SettlementService {

        SettlementDTO create(SettlementDTO dto);
        SettlementDTO getById(String id);
        List<SettlementDTO> listByGroup(String groupId);
        List<SettlementDTO> listByUser(String userId);
        void delete(String id);

}
