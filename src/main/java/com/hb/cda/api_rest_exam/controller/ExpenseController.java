package com.hb.cda.api_rest_exam.controller;

import com.hb.cda.api_rest_exam.dto.ExpenseDTO;
import com.hb.cda.api_rest_exam.dto.ExpenseShareDTO;
import com.hb.cda.api_rest_exam.dto.SplitExpenseRequestDTO;
import com.hb.cda.api_rest_exam.service.ExpenseService;
import com.hb.cda.api_rest_exam.service.ExpenseShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseShareService expenseShareService;

    @PostMapping
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO dto) {
        ExpenseDTO expenseDTO = expenseService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable String id) {
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ExpenseDTO>> getExpensesByGroup(
            @PathVariable String groupId,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount,
            @RequestParam(required = false) String userId
    )
    {
        return ResponseEntity.ok(expenseService.getExpensesByGroup(groupId));
    }

    @GetMapping("/{expenseId}/shares")
    public ResponseEntity<List<ExpenseShareDTO>> getShares(@PathVariable String expenseId) {
        return ResponseEntity.ok(expenseShareService.getSharesByExpense(expenseId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String id) {
        expenseService.deleteExpenseById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/split")
    public ResponseEntity<ExpenseDTO> splitExpense(@RequestBody SplitExpenseRequestDTO dto) {
        return ResponseEntity.ok(expenseService.splitExpense(dto));
    }
}
