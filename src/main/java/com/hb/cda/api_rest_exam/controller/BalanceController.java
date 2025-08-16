package com.hb.cda.api_rest_exam.controller;

import com.hb.cda.api_rest_exam.dto.BalanceDTO;
import com.hb.cda.api_rest_exam.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/groups/{groupId}/balances")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;

    @GetMapping
    public ResponseEntity<List<BalanceDTO>> getBalances(@PathVariable String groupId) {
        return ResponseEntity.ok(balanceService.calculateBalances(groupId));
    }
}