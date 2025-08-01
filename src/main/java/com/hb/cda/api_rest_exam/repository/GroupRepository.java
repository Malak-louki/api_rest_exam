package com.hb.cda.api_rest_exam.repository;

import com.hb.cda.api_rest_exam.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

}
