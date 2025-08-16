package com.hb.cda.api_rest_exam.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hb.cda.api_rest_exam.dto.GroupDTO;
import com.hb.cda.api_rest_exam.entity.Group;
import com.hb.cda.api_rest_exam.entity.User;
import com.hb.cda.api_rest_exam.repository.GroupRepository;
import com.hb.cda.api_rest_exam.repository.UserRepository;
import com.hb.cda.api_rest_exam.service.implementation.GroupServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    void createGroup_ShouldReturnGroupDTO() {
        // Arrange
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName("Test Group");
        groupDTO.setDescription("Description");
        groupDTO.setUserIds(Set.of("user1", "user2"));

        User user1 = new User(); user1.setId("user1");
        User user2 = new User(); user2.setId("user2");

        when(userRepository.findById("user1")).thenReturn(Optional.of(user1));
        when(userRepository.findById("user2")).thenReturn(Optional.of(user2));

        Group groupToSave = new Group();
        groupToSave.setName(groupDTO.getName());
        groupToSave.setDescription(groupDTO.getDescription());
        groupToSave.setUsers(Set.of(user1, user2));

        when(groupRepository.save(any(Group.class))).thenReturn(groupToSave);

        // Act
        GroupDTO result = groupService.create(groupDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Test Group", result.getName());
        assertEquals(2, result.getUserIds().size());
    }
}