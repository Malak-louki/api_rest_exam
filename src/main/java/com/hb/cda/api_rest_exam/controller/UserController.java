package com.hb.cda.api_rest_exam.controller;

import com.hb.cda.api_rest_exam.dto.ExpenseDTO;
import com.hb.cda.api_rest_exam.dto.GroupDTO;
import com.hb.cda.api_rest_exam.dto.UserCreateDTO;
import com.hb.cda.api_rest_exam.dto.UserDTO;
import com.hb.cda.api_rest_exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO dto ){
        UserDTO userDTO = userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String id){
        UserDTO userDTO = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/{id}/groups")
    public ResponseEntity<List<GroupDTO>> getUserGroups(@PathVariable String id) {
        return ResponseEntity.ok(userService.getGroupsByUserId(id));
    }

    @GetMapping("/{id}/expenses")
    public ResponseEntity<List<ExpenseDTO>> getUserExpenses(@PathVariable String id) {
        return ResponseEntity.ok((userService.getExpensesByUserId(id)));
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

}
