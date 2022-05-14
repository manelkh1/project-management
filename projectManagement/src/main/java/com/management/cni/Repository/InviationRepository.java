package com.management.cni.Repository;

import com.management.cni.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviationRepository extends JpaRepository<Invitation, Long> {

  List<Invitation> findInvitationByProject(Project project);

  List<Invitation> findInvitationByStatus(Status status);

  List<Invitation> findInvitationByManager(Manager manager);

  List<Invitation> findInvitationByMember(Member member);

  Invitation findInvitationByMemberAndProject(Member member, Project project);

  List<Invitation> findInvitationByMemberAndStatus(Member member, Status status);

  List<Invitation> findInvitationByMemberAndStatusAndProject(Member member, Project project, Status status);

  List<Invitation> findInvitationByManagerAndStatus(Manager manager, Status status);
  //	List<Invitation> findInvitationByUser(User user);
}
