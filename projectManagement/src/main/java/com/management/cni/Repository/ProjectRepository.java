package com.management.cni.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.management.cni.Entity.Project;
import com.management.cni.Entity.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
	//request to get all user's project 
@Query(value ="SELECT * FROM t_project WHERE id_user =:userId")
	List<Project> getProjectByUserId(Long userId);
}
