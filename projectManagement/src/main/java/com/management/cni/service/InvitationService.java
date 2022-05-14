package com.management.cni.service;

import com.management.cni.Entity.*;
import com.management.cni.Repository.InviationRepository;
import com.management.cni.Repository.MemberRepository;
import com.management.cni.Repository.ProjectRepository;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.InvitationRequest;
import com.management.cni.security.dto.response.InvitationResponse;
import com.management.cni.security.mapper.InvitationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvitationService {

  @Autowired
  private InviationRepository inviationRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private ProjectRepository projectRepository;
  @Autowired
  private MemberRepository memberRepository;

  /// for member consult recieved Invitation
  public ApiResponse getAllInvitationByMember() {

    List<InvitationResponse> invitationsResponse = new ArrayList<>();
    try {

      User user = userService.getConnectedMember();
      if (user != null) {
        List<Invitation> invitations = inviationRepository.findInvitationByMember(user.getMember());

        if (!invitations.isEmpty()) {
          for (Invitation invitation : invitations) {
            InvitationResponse invitationResponse = InvitationMapper.INSTANCE.convertToInvitationResponse(invitation);
            invitationsResponse.add(invitationResponse);
          }
        }
        return new ApiResponse(invitationsResponse, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse getAllInvitationByProject(long idProject) {

    List<InvitationResponse> invitationsResponse = new ArrayList<>();
    try {

      User user = userService.getConnectedManager();
      if (user != null) {
        Project project = projectRepository.getById(idProject);
        if (project == null) {
          return new ApiResponse(null, "PROJECT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        List<Invitation> invitations = inviationRepository.findInvitationByProject(project);

        if (!invitations.isEmpty()) {
          for (Invitation invitation : invitations) {
            InvitationResponse invitationResponse = InvitationMapper.INSTANCE.convertToInvitationResponse(invitation);
            invitationsResponse.add(invitationResponse);
          }
        }
        return new ApiResponse(invitationsResponse, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse acceptInvitationByProject(long idProject) {

    try {

      User user = userService.getConnectedMember();
      if (user != null) {
        Project project = projectRepository.getById(idProject);
        if (project != null) {

          Invitation invitation = inviationRepository.findInvitationByMemberAndProject(user.getMember(), project);

          if (invitation != null) {
            invitation.setStatus(Status.ACCEPTED);
            inviationRepository.save(invitation);
            return new ApiResponse(null, "INVIATION ACCEPETED", HttpStatus.OK, LocalDateTime.now());
          } else {
            return new ApiResponse(null, "INVIATION DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
          }
        } else {
          return new ApiResponse(null, "PROJECT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse refuseInvitationByProject(long idProject) {

    try {

      User user = userService.getConnectedManager();
      if (user != null) {
        Project project = projectRepository.getById(idProject);
        if (project == null) {

          Invitation invitation = inviationRepository.findInvitationByMemberAndProject(user.getMember(), project);

          if (invitation != null) {
            invitation.setStatus(Status.REFUSED);
            inviationRepository.save(invitation);
            return new ApiResponse(null, "INVIATION REFUSED", HttpStatus.OK, LocalDateTime.now());
          } else {
            return new ApiResponse(null, "INVIATION DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
          }
        } else {
          return new ApiResponse(null, "PROJECT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse sendInvitationByProject(InvitationRequest invitationRequest) {

    try {
      User user = userService.getConnectedManager();
      if (user != null) {
        Project project = projectRepository.getById(invitationRequest.getProjectId());
        Member member = memberRepository.getById(invitationRequest.getMemberId());
        if (project == null) {
          return new ApiResponse(null, "PROJECT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        if (member == null) {
          return new ApiResponse(null, "MEMBER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        Invitation invitation = InvitationMapper.INSTANCE.convertToInvitation(invitationRequest);
        invitation.setManager(user.getManager());
        invitation.setDate(Timestamp.from(Instant.now()));
        invitation.setProject(project);
        invitation.setMember(member);
        invitation.setStatus(Status.PENDING);

        inviationRepository.save(invitation);
        return new ApiResponse(null, "INVITATION SENDED", HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse removeInvitationByProject(long idInvitation) {

    try {

      User user = userService.getConnectedManager();
      if (user != null) {
        Invitation invitation = inviationRepository.getById(idInvitation);
        if (invitation == null) {
          inviationRepository.deleteById(idInvitation);
          return new ApiResponse(null, "INVITATION REMOVED", HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "INVITATION DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

}
