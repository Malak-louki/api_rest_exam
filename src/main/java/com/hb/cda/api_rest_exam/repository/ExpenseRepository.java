package com.hb.cda.api_rest_exam.repository;

import com.hb.cda.api_rest_exam.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository

public interface ExpenseRepository  extends JpaRepository<Expense, String> {
    List<Expense> findByUserId(String userId);
    List<Expense> findByGroupId(String groupId);
    List<Expense> findByGroupIdAndAmountGreaterThan(String groupId, BigDecimal amount);
    List<Expense> findByGroupIdAndAmountLessThan(String groupId, BigDecimal amount);
    List<Expense> findByGroupIdAndUser_Id(String groupId, String userId);
}
