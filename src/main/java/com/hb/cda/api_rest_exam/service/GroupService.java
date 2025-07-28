package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.GroupDTO;

import java.util.List;

public interface GroupService {
    public List<GroupDTO> findAll();
    public GroupDTO create(GroupDTO groupDTO);
    public GroupDTO findById(String id);
    public void delete(String id);
    public void addMemberToGroup(String groupId, String userId);
    public void removeMemberFromGroup(String groupId, String userId);

}
