package com.hb.cda.api_rest_exam.service;

import com.hb.cda.api_rest_exam.dto.GroupDTO;
import com.hb.cda.api_rest_exam.dto.UserDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface GroupService {
    List<GroupDTO> findAll();
    GroupDTO create(GroupDTO groupDTO);
    GroupDTO findById(String id);
    List<UserDTO> getMembers(String groupId);
    List<GroupDTO> getGroupsByUser(String userId);
    Map<String, BigDecimal> calculateBalances(String groupId);
    void delete(String id);
    void addMemberToGroup(String groupId, String userId);
    void removeMemberFromGroup(String groupId, String userId);

}
