package com.hb.cda.api_rest_exam.service.implementation;

import com.hb.cda.api_rest_exam.dto.SettlementDTO;
import com.hb.cda.api_rest_exam.entity.Group;
import com.hb.cda.api_rest_exam.entity.Settlement;
import com.hb.cda.api_rest_exam.entity.User;
import com.hb.cda.api_rest_exam.mapper.SettlementMapper;
import com.hb.cda.api_rest_exam.repository.GroupRepository;
import com.hb.cda.api_rest_exam.repository.SettlementRepository;
import com.hb.cda.api_rest_exam.repository.UserRepository;
import com.hb.cda.api_rest_exam.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {
    private final SettlementRepository settlementRepository;
    private final UserRepository userRepository;
    private  final GroupRepository groupRepository;

    @Override
    public SettlementDTO create(SettlementDTO dto) {
        User fromUSer = userRepository.findById(dto.getFromUserId())
                .orElseThrow(()-> new RuntimeException("From user not found"));
        User toUser = userRepository.findById(dto.getToUserId())
                .orElseThrow(()-> new RuntimeException("To user not found"));
        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(()-> new RuntimeException("Group not found"));

        Settlement settlement = SettlementMapper.toSettlement(dto, fromUSer, toUser, group);

        Settlement savedSettlement = settlementRepository.save(settlement);

        return SettlementMapper.toSettlementDTO(savedSettlement);
    }

    @Override
    public SettlementDTO getById(String id) {

        return settlementRepository.findById(id)
                .map(SettlementMapper::toSettlementDTO)
                .orElseThrow(()-> new RuntimeException("Settlement not found"));
    }

    @Override
    public List<SettlementDTO> listByGroup(String groupId) {
        groupRepository.findById(groupId)
                .orElseThrow(()-> new RuntimeException("Group not found"));
        List<Settlement> settlements = settlementRepository.findByGroup_Id(groupId);

        return settlements.stream()
                .map(SettlementMapper::toSettlementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SettlementDTO> listByUser(String userId) {
        userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        List<Settlement> settlements = settlementRepository.findByFromUser_IdOrToUser_Id(userId, userId);

        return settlements.stream()
                .map(SettlementMapper::toSettlementDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if (!settlementRepository.existsById(id)) {
            throw new RuntimeException("Settlement not found");
        }
        settlementRepository.deleteById(id);

    }
}
