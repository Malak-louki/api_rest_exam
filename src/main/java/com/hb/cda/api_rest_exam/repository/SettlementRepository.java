package com.hb.cda.api_rest_exam.repository;

import com.hb.cda.api_rest_exam.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, String> {
    List<Settlement> findByGroup_Id(String groupId);
    List<Settlement> findByFromUser_IdOrToUser_Id(String fromUserId, String toUserId);
}
