package com.management.cni.repository;

import com.management.cni.entity.Manager;
import com.management.cni.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
  //request to get all user's project
/*@Query(value ="SELECT * FROM t_project WHERE id_user =:userId")
	List<Project> getProjectByUserId(Long userId);*/

  List<Project> findAllByStatus(Project project);

  Project findProjectByTitle(String title);

  List<Project> findAllByManager(Manager manager);

  Project findProjectByManager(Manager manager);

}
