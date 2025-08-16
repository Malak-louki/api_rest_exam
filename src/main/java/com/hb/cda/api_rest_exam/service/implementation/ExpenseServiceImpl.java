package com.hb.cda.api_rest_exam.service.implementation;

import com.hb.cda.api_rest_exam.dto.ExpenseDTO;
import com.hb.cda.api_rest_exam.dto.SplitExpenseRequestDTO;
import com.hb.cda.api_rest_exam.entity.Expense;
import com.hb.cda.api_rest_exam.entity.ExpenseShare;
import com.hb.cda.api_rest_exam.entity.Group;
import com.hb.cda.api_rest_exam.entity.User;
import com.hb.cda.api_rest_exam.exception.BadRequestException;
import com.hb.cda.api_rest_exam.exception.ResourceNotFoundException;
import com.hb.cda.api_rest_exam.mapper.ExpenseMapper;
import com.hb.cda.api_rest_exam.repository.ExpenseRepository;
import com.hb.cda.api_rest_exam.repository.GroupRepository;
import com.hb.cda.api_rest_exam.repository.UserRepository;
import com.hb.cda.api_rest_exam.service.ExpenseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Override
    public ExpenseDTO create(ExpenseDTO dto) {
        Expense expense = ExpenseMapper.toExpense(dto);
        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.toExpenseDTO(savedExpense);
    }

    @Override
    public List<ExpenseDTO> findAll() {
        return expenseRepository.findAll().stream()
                .map(ExpenseMapper::toExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExpenseDTO getExpenseById(String id) {
        return expenseRepository.findById(id)
                .map(ExpenseMapper::toExpenseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
    }

    @Override
    public List<ExpenseDTO> getExpensesByGroup(String groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + groupId));

        return group.getExpenses().stream()
                .map(ExpenseMapper::toExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ExpenseDTO> getExpenseByUser(String userId) {
        return expenseRepository.findByUserId(userId).stream()
                .map(ExpenseMapper::toExpenseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteExpenseById(String id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense not found with id: " + id);
        }
        expenseRepository.deleteById(id);
    }

    @Override
    @Transactional
    public ExpenseDTO splitExpense(SplitExpenseRequestDTO dto) {
        // Récupérer le payeur depuis le DTO (tu dois l'ajouter dans SplitExpenseRequestDTO)
        User payer = userRepository.findById(dto.getPayerId())
                .orElseThrow(() -> new ResourceNotFoundException("Payer not found"));

        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("Group not found"));

        if (!group.getUsers().contains(payer)) {
            throw new BadRequestException("Payer is not a member of this group");
        }

        Expense expense = new Expense();
        expense.setLabel(dto.getDescription());
        expense.setAmount(dto.getMontantTotal());
        expense.setUser(payer);
        expense.setGroup(group);
        expense.setExpenseDate(LocalDate.now());

        // Calcul des parts égales
        BigDecimal shareAmount = dto.getMontantTotal()
                .divide(BigDecimal.valueOf(group.getUsers().size()), 2, RoundingMode.HALF_UP);
        // Création des parts pour chaque membre
        group.getUsers().forEach(member -> {
            ExpenseShare share = new ExpenseShare();
            share.setAmount(shareAmount);
            share.setUser(member);
            share.setExpense(expense);
            share.setIsPaid(member.equals(payer)); // Le payeur a déjà payé sa part
            expense.getShares().add(share);
        });

        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseMapper.toExpenseDTO(savedExpense);
    }
}



