package com.hb.cda.api_rest_exam.service;


import com.hb.cda.api_rest_exam.dto.GroupDTO;
import com.hb.cda.api_rest_exam.entity.Group;
import com.hb.cda.api_rest_exam.entity.User;
import com.hb.cda.api_rest_exam.repository.GroupRepository;
import com.hb.cda.api_rest_exam.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest
class GroupServiceFunctionalTest {
    @Autowired
    private GroupService groupService;
    private UserRepository userRepository;

    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        public GroupRepository groupRepository() {
            return mock(GroupRepository.class);
        }
    }

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void addMemberToGroup_ShouldAddUserToGroup() {
        // Arrange
        String groupId = "group1";
        String userId = "user1";

        Group group = new Group();
        group.setId(groupId);

        User user = new User();
        user.setId(userId);

        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(groupRepository.save(any(Group.class))).thenReturn(group);

        // Act
        groupService.addMemberToGroup(groupId, userId);

        // Assert
        assertTrue(group.getUsers().contains(user));
    }
}
