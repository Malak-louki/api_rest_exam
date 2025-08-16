package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.BalanceDTO;
import com.hb.cda.api_rest_exam.entity.ExpenseShare;
import com.hb.cda.api_rest_exam.entity.User;
import com.hb.cda.api_rest_exam.repository.ExpenseShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final ExpenseShareRepository expenseShareRepository;

    public List<BalanceDTO> calculateBalances(String groupId) {
        // 1. Calculer ce que chaque membre a payé et doit
        Map<User, BigDecimal> balances = expenseShareRepository.findByExpense_Group_Id(groupId)
                .stream()
                .collect(Collectors.groupingBy(
                        ExpenseShare::getUser,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                share -> share.getIsPaid() ? share.getAmount() : share.getAmount().negate(),
                                BigDecimal::add
                        )
                ));

        // 2. Simplifier les dettes (algorithme minimal)
        List<BalanceDTO> debts = new ArrayList<>();
        List<User> users = new ArrayList<>(balances.keySet());

        for (User debtor : users) {
            for (User creditor : users) {
                if (balances.get(debtor).compareTo(BigDecimal.ZERO) < 0
                        && balances.get(creditor).compareTo(BigDecimal.ZERO) > 0) {

                    BigDecimal amount = balances.get(debtor).negate().min(balances.get(creditor));
                    if (amount.compareTo(BigDecimal.ZERO) > 0) {
                        debts.add(new BalanceDTO(
                                debtor.getId(),
                                creditor.getId(),
                                amount
                        ));
                        balances.put(debtor, balances.get(debtor).add(amount));
                        balances.put(creditor, balances.get(creditor).subtract(amount));
                    }
                }
            }
        }

        return debts;
    }
}
