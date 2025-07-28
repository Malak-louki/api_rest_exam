package com.hb.cda.api_rest_exam.repository;

import com.hb.cda.api_rest_exam.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense, String> {
    List<Expense> findByUserId(String userId);
    List<Expense> findByGroupId(String groupId);
}
