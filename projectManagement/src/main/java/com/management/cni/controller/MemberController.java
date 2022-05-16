package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.MemberRequest;
import com.management.cni.service.MemberService;
import com.management.cni.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/members")
public class MemberController {

  @Autowired
  private MemberService memberService;
  @Autowired
  private ProjectService projectService;

  //get all the members list
  @GetMapping("")
  public ResponseEntity<ApiResponse> getAllMembers() {
    ApiResponse apiResponse = memberService.getAllMembers();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  //get member details
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse> getMemberById(@PathVariable("id") long id) {
    ApiResponse apiResponse = memberService.getMemberById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  //get the members of each project
  @GetMapping("/projects/{id}")
  public ResponseEntity<ApiResponse> getAllMembersByProject(@PathVariable("id") long id) {
    ApiResponse apiResponse = projectService.getAllMembersByProject(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  // add member
  @PostMapping("")
  public ResponseEntity<ApiResponse> createMember(@RequestBody MemberRequest memberRequest) {
    ApiResponse apiResponse = memberService.createMember(memberRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse> updateMember(@PathVariable("id") long id, @RequestBody MemberRequest memberRequest) {
    ApiResponse apiResponse = memberService.updateMember(id, memberRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteMemberById(@PathVariable("id") long id) {
    ApiResponse apiResponse = memberService.deleteMemberById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

}
