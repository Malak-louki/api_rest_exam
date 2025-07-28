package com.hb.cda.api_rest_exam.service.implementation;

import com.hb.cda.api_rest_exam.dto.GroupDTO;
import com.hb.cda.api_rest_exam.dto.UserDTO;
import com.hb.cda.api_rest_exam.entity.Group;
import com.hb.cda.api_rest_exam.entity.User;
import com.hb.cda.api_rest_exam.mapper.GroupMapper;
import com.hb.cda.api_rest_exam.repository.GroupRepository;
import com.hb.cda.api_rest_exam.repository.UserRepository;
import com.hb.cda.api_rest_exam.service.GroupService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    @Override
    public List<GroupDTO> findAll() {
        return groupRepository.findAll().stream()
                .map(GroupMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDTO create(GroupDTO groupDTO) {
        Set<User> users = groupDTO.getUserIds().stream()
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found: " + userId)))
                .collect(Collectors.toSet());

        Group group = GroupMapper.toGroup(groupDTO, users);
        Group saved = groupRepository.save(group);
        return GroupMapper.toDTO(saved);
    }
    @Override
    public GroupDTO findById(String id) {
        return groupRepository.findById(id)
                .map(GroupMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("group not found"));
    }

    @Override
    public void delete(String id) {
        if (!groupRepository.existsById(id)) {
            throw new RuntimeException("group not found");
        }
        groupRepository.deleteById(id);
    }

    @Override
    public void addMemberToGroup(String groupId, String userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        if(group.getUsers().contains(user)) {
            throw new RuntimeException("user already a member of this group");
        }
        group.getUsers().add(user);
        groupRepository.save(group);

    }

    @Override
    public void removeMemberFromGroup(String groupId, String userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));
        if(group.getUsers().contains(user)) {
            group.getUsers().remove(user);
            groupRepository.save(group);
        }else {
            throw new EntityNotFoundException("user not a member of this group");
        }

    }
}
