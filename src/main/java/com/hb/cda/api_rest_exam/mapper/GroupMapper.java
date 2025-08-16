package com.hb.cda.api_rest_exam.mapper;

import com.hb.cda.api_rest_exam.dto.GroupDTO;
import com.hb.cda.api_rest_exam.entity.Group;
import com.hb.cda.api_rest_exam.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupMapper {

    public static GroupDTO toGroupDTO(Group group) {
        if(group == null) return null;

        GroupDTO dto = new GroupDTO();
        dto.setId(group.getId());
        dto.setName(group.getName());
        dto.setDescription(group.getDescription());
        dto.setCreationDate(group.getCreationDate());
        dto.setUserIds(
                group.getUsers().stream()
                        .map(user -> user.getId())
                        .collect(Collectors.toSet())
        );
        return dto;
    }
    public static Group toGroup(GroupDTO dto, Set<User> users) {
        if(dto == null) return null;
        Group group = new Group();
        group.setId(dto.getId());
        group.setName(dto.getName());
        group.setDescription(dto.getDescription());
        group.setCreationDate(dto.getCreationDate());
        group.setUsers(users);
        return group;
    }

}
