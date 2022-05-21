package com.management.cni.repository;

import com.management.cni.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviationRepository extends JpaRepository<Invitation, Long> {

  List<Invitation> findInvitationsByProject(Project project);

  List<Invitation> findInvitationsByStatus(Status status);

  List<Invitation> findInvitationsByManager(Manager manager);

  List<Invitation> findInvitationsByMember(Member member);

  Invitation findInvitationByMemberAndProject(Member member, Project project);
  Invitation findInvitationByMember(Member member);
  List<Invitation> findInvitationByMemberAndStatus(Member member, Status status);

  List<Invitation> findInvitationByMemberAndStatusAndProject(Member member, Project project, Status status);

  List<Invitation> findInvitationByManagerAndStatus(Manager manager, Status status);
  //	List<Invitation> findInvitationByUser(User user);
}
