package com.hb.cda.api_rest_exam.repository;

import com.hb.cda.api_rest_exam.entity.ExpenseShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseShareRepository extends JpaRepository<ExpenseShare, String> {
    List<ExpenseShare> findByUserId(String userId);
    List<ExpenseShare> findByExpense_Group_Id(String groupId);

}
