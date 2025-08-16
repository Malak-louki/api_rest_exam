package com.hb.cda.api_rest_exam.controller;

import com.hb.cda.api_rest_exam.dto.GroupDTO;
import com.hb.cda.api_rest_exam.dto.UserDTO;

import com.hb.cda.api_rest_exam.service.GroupService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupDTO> addGroup(@RequestBody GroupDTO dto) {
        GroupDTO addGroupDTO = groupService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addGroupDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable String id) {
        GroupDTO groupDTO = groupService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(groupDTO);
    }

    @GetMapping("/{groupId}/members")
    public ResponseEntity<List<UserDTO>> getMembers(@PathVariable String groupId) {
        return ResponseEntity.ok(groupService.getMembers(groupId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroupDTO>> getGroupsByUser(@PathVariable String userId) {
        return ResponseEntity.ok(groupService.getGroupsByUser(userId));
    }

    @PreAuthorize("@groupSecurity.isMember(#groupId, authentication.principal.id)")
    @PostMapping("/{groupId}/members/{userId}")
    public ResponseEntity<Void> addMember(
            @PathVariable String groupId,
            @PathVariable String userId) {
        groupService.addMemberToGroup(groupId, userId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("@groupSecurity.isAdmin(#groupId, authentication.principal.id)")
    @DeleteMapping("/{groupId}/members/{userId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable String groupId,
            @PathVariable String userId) {
        groupService.removeMemberFromGroup(groupId, userId);
        return ResponseEntity.noContent().build();
    }
}
