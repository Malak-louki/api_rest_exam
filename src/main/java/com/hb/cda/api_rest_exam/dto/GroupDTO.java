package com.hb.cda.api_rest_exam.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
@Data
public class GroupDTO {
    private String id;
    private String name;
    private String description;
    private LocalDate creationDate;
    private Set<String> userIds;
}
