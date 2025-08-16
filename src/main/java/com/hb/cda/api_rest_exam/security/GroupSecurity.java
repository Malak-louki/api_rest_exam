package com.hb.cda.api_rest_exam.security;

import com.hb.cda.api_rest_exam.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GroupSecurity {
    private final GroupRepository groupRepository;

    public boolean isMember(String groupId, String userId) {
        return groupRepository.findById(groupId)
                .map(group -> group.getUsers().stream()
                        .anyMatch(user -> user.getId().equals(userId)))
                .orElse(false);
    }

    public boolean isAdmin(String groupId, String userId) {

        return isMember(groupId, userId);
    }
}