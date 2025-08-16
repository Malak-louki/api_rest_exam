package com.hb.cda.api_rest_exam.service.implementation;

import com.hb.cda.api_rest_exam.dto.GroupDTO;
import com.hb.cda.api_rest_exam.dto.UserDTO;
import com.hb.cda.api_rest_exam.entity.Expense;
import com.hb.cda.api_rest_exam.entity.ExpenseShare;
import com.hb.cda.api_rest_exam.entity.Group;
import com.hb.cda.api_rest_exam.entity.User;
import com.hb.cda.api_rest_exam.exception.BadRequestException;
import com.hb.cda.api_rest_exam.exception.ResourceNotFoundException;
import com.hb.cda.api_rest_exam.mapper.GroupMapper;
import com.hb.cda.api_rest_exam.mapper.UserMapper;
import com.hb.cda.api_rest_exam.repository.ExpenseRepository;
import com.hb.cda.api_rest_exam.repository.GroupRepository;
import com.hb.cda.api_rest_exam.repository.UserRepository;
import com.hb.cda.api_rest_exam.service.GroupService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository; // Ajouté pour calculateBalances

    @Override
    public List<GroupDTO> findAll() {
        return groupRepository.findAll().stream()
                .map(GroupMapper::toGroupDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GroupDTO create(GroupDTO groupDTO) {
        Group group = new Group();
        group.setName(groupDTO.getName());
        group.setDescription(groupDTO.getDescription());
        group.setCreationDate(LocalDate.now());

        if (groupDTO.getUserIds() != null) {
            Set<User> members = groupDTO.getUserIds().stream()
                    .map(userId -> userRepository.findById(userId))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
            group.setUsers(members);
        }

        Group savedGroup = groupRepository.save(group);
        return GroupMapper.toGroupDTO(savedGroup);
    }

    @Override
    public GroupDTO findById(String id) {
        return groupRepository.findById(id)
                .map(GroupMapper::toGroupDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));
    }

    @Override
    public List<UserDTO> getMembers(String groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));

        return group.getUsers().stream()
                .map(UserMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupDTO> getGroupsByUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return user.getGroups().stream()
                .map(GroupMapper::toGroupDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Map<String, BigDecimal> calculateBalances(String groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));

        // 1. Calculer ce que chaque membre a payé
        Map<String, BigDecimal> paidAmounts = group.getExpenses().stream()
                .collect(Collectors.groupingBy(
                        e -> e.getUser().getId(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Expense::getAmount,
                                BigDecimal::add)
                ));

        // 2. Calculer ce que chaque membre doit
        Map<String, BigDecimal> owedAmounts = group.getExpenses().stream()
                .flatMap(e -> e.getShares().stream())
                .collect(Collectors.groupingBy(
                        s -> s.getUser().getId(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                ExpenseShare::getAmount,
                                BigDecimal::add)
                ));

        // 3. Calculer le solde (payé - dû)
        Map<String, BigDecimal> balances = new HashMap<>();
        group.getUsers().forEach(user -> {
            BigDecimal paid = paidAmounts.getOrDefault(user.getId(), BigDecimal.ZERO);
            BigDecimal owed = owedAmounts.getOrDefault(user.getId(), BigDecimal.ZERO);
            balances.put(user.getId(), paid.subtract(owed));
        });

        return balances;
    }

    @Override
    @Transactional
    public void delete(String id) {
        if (!groupRepository.existsById(id)) {
            throw new ResourceNotFoundException("Group not found");
        }
        groupRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addMemberToGroup(String groupId, String userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (group.getUsers().contains(user)) {
            throw new BadRequestException("User is already a member of this group");
        }

        group.getUsers().add(user);
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void removeMemberFromGroup(String groupId, String userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!group.getUsers().contains(user)) {
            throw new BadRequestException("User is not a member of this group");
        }

        group.getUsers().remove(user);
        groupRepository.save(group);
    }
}
