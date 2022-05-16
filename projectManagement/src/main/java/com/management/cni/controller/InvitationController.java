package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.InvitationRequest;
import com.management.cni.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/invitations")
public class InvitationController {

  @Autowired
  private InvitationService invitationService;


  // get all the invitations of a member
  @GetMapping("/")
  public ResponseEntity<ApiResponse> getAllInvitationByMember() {
    ApiResponse apiResponse = invitationService.getAllInvitationByMember();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  //get all the invitations in a projet
  @GetMapping("/{idProject}")
  public ResponseEntity<ApiResponse> getAllInvitationByProject(@PathVariable long idProject) {
    ApiResponse apiResponse = invitationService.getAllInvitationByProject(idProject);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }


  @PostMapping("/acceptInvitation/{idProject}")
  public ResponseEntity<ApiResponse> acceptInvitationByProject(@PathVariable long idProject) {
    ApiResponse apiResponse = invitationService.acceptInvitationByProject(idProject);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("/refuseInvitation/{idProject}")
  public ResponseEntity<ApiResponse> refuseInvitationByProject(@PathVariable long idProject) {
    ApiResponse apiResponse = invitationService.refuseInvitationByProject(idProject);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  //////////***** @PathVariable idproject
  @PostMapping("/sendInvitation")
  public ResponseEntity<ApiResponse> sendInvitationByProject(@RequestBody InvitationRequest invitationRequest) {
    ApiResponse apiResponse = invitationService.sendInvitationByProject(invitationRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("/removeInvitation/{idProject}")
  public ResponseEntity<ApiResponse> removeInvitationByProject(@PathVariable long idProject) {
    ApiResponse apiResponse = invitationService.removeInvitationByProject(idProject);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

}


