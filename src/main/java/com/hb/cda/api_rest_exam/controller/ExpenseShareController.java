package com.hb.cda.api_rest_exam.controller;

import com.hb.cda.api_rest_exam.service.ExpenseShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/expenseshare")
public class ExpenseShareController {
    private final ExpenseShareService expenseShareService;

//    @PostMapping
//    public String addExpenseShare(@RequestBody ExpenseShareDTO dto) {
//        ExpenseDTO expenseDTO = expenseShareService;
//    }
}
