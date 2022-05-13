package com.management.cni.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.management.cni.Entity.Invitation;
import com.management.cni.Entity.Project;
import com.management.cni.Entity.User;

@Repository
public interface InviationRepository extends JpaRepository<Invitation, Long >{

	List<Invitation> findInvitationByProject(Project project);
	List<Invitation> findInvitationByUser(User user);
}
