package com.hb.cda.api_rest_exam.controller;

import com.hb.cda.api_rest_exam.dto.SettlementDTO;

import com.hb.cda.api_rest_exam.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settlements")
@RequiredArgsConstructor
public class SettlementController {
    private final SettlementService settlementService;

    @PostMapping
    public ResponseEntity<SettlementDTO> create(@RequestBody SettlementDTO dto) {
        SettlementDTO createdSettlement = settlementService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSettlement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SettlementDTO> getById(@PathVariable String id) {
        SettlementDTO settlementDTO = settlementService.getById(id);
        return ResponseEntity.ok(settlementDTO);
    }


    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<SettlementDTO>> listByGroup(@PathVariable String groupId) {
        List<SettlementDTO> settlements = settlementService.listByGroup(groupId);
        return ResponseEntity.ok(settlements);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SettlementDTO>> listByUser(@PathVariable String userId) {
        List<SettlementDTO> settlements = settlementService.listByUser(userId);
        return ResponseEntity.ok(settlements);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        settlementService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
