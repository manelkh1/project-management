package com.management.cni.service;

import com.management.cni.entity.*;
import com.management.cni.repository.InviationRepository;
import com.management.cni.repository.MemberRepository;
import com.management.cni.repository.ProjectRepository;
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

  public ApiResponse getAllInvitationByManager() {

    List<InvitationResponse> invitationsResponse = new ArrayList<>();
    try {
      /// the manager is connected
      User user = userService.getConnectedManager();
      if (user != null) {
        List<Invitation> invitations = inviationRepository.findInvitationsByManager(user.getManager());
        if (!invitations.isEmpty()) {
          /// invitation list from findInvitationsByManager
          for (Invitation invitation : invitations) {
            /// convert to invitationResponse
            InvitationResponse invitationResponse = InvitationMapper.INSTANCE.convertToInvitationResponse(invitation);
            invitationsResponse.add(invitationResponse);
          }
        }
        return new ApiResponse(invitationsResponse, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE MANAGER", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  /// for member consult recieved Invitation
  public ApiResponse getAllInvitationByMember() {
    List<InvitationResponse> invitationsResponse = new ArrayList<>();
    try {
      User user = userService.getConnectedMember();
      if (user != null) {
        List<Invitation> invitations = inviationRepository.findInvitationsByMember(user.getMember());
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
        List<Invitation> invitations = inviationRepository.findInvitationsByProject(project);

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

  public ApiResponse sendInvitationByProject(InvitationRequest invitationRequest) {

    try {
      /// il doit etre un manager connectée
      User user = userService.getConnectedManager();
      if (user != null) {
        // project = idProject du invitationRequest
        Project project = projectRepository.getById(invitationRequest.getProjectId());
        /// member = idMember du invitationRequest
        Member member = memberRepository.getById(invitationRequest.getMemberId());
        if (project == null) {
          return new ApiResponse(null, "PROJECT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        if (member == null) {
          return new ApiResponse(null, "MEMBER DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        Invitation invitation = InvitationMapper.INSTANCE.convertToInvitation(invitationRequest);
        /// set manager , date , project , member
        invitation.setManager(user.getManager());
        invitation.setDate(Timestamp.from(Instant.now()));
        invitation.setProject(project);
        invitation.setMember(member);
        /// passer le status PENDING lors de l'envoi
        invitation.setStatus(Status.PENDING);
        /// enregistrer l'invitation
        inviationRepository.save(invitation);
        return new ApiResponse(null, "INVITATION SENDED", HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE MANAGER", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse removeInvitationByProject(long idInvitation) {
/// if the manager is connected
    try {
      User user = userService.getConnectedManager();
      if (user != null) {
        Invitation invitation = inviationRepository.getById(idInvitation);
        if (invitation != null) {
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

  public ApiResponse acceptInvitationByProject(long idInvitation) {
    try {
      /// member is connected
      User user = userService.getConnectedMember();
      if (user != null) {
        /// findInvitationsByMember member invitations
        List<Invitation> invitations = inviationRepository.findInvitationsByMember(user.getMember());
        /// iteration of the invitation list
        for (Invitation invitation : invitations) {
          /// invitation exist
          if (invitation != null && invitation.getId() == idInvitation) {
            /// set status of the invitation to ACCEPTED
            invitation.setStatus(Status.ACCEPTED);
            inviationRepository.save(invitation);
            return new ApiResponse(null, "INVIATION REFUSED", HttpStatus.OK, LocalDateTime.now());
          }
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
      return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());

    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse refuseInvitationByProject(long idInvitation) {
    try {
      /// a member is connected
      User user = userService.getConnectedMember();
      if (user != null) {
        List<Invitation> invitations = inviationRepository.findInvitationsByMember(user.getMember());
        for (Invitation invitation : invitations) {
          if (invitation != null && invitation.getId() == idInvitation) {
            /// change the invitation status to REFUSED
            invitation.setStatus(Status.REFUSED);
            inviationRepository.save(invitation);
            return new ApiResponse(null, "INVITATION REFUSED", HttpStatus.OK, LocalDateTime.now());
          }
        }
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
      return new ApiResponse(null, null, HttpStatus.NO_CONTENT, LocalDateTime.now());
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }
}
