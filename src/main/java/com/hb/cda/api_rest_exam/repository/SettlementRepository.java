package com.hb.cda.api_rest_exam.repository;

import com.hb.cda.api_rest_exam.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, String> {
}
