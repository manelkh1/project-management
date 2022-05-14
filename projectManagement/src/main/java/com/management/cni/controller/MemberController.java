package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.MemberRequest;
import com.management.cni.service.MemberService;
import com.management.cni.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class MemberController {

  @Autowired
  private MemberService memberService;
  @Autowired
  private ProjectService projectService;

  @GetMapping("/members")
  public ResponseEntity<ApiResponse> getAllMembers() {
    ApiResponse apiResponse = memberService.getAllMembers();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("/members/{id}")
  public ResponseEntity<ApiResponse> getMemberById(@PathVariable("id") long id) {
    ApiResponse apiResponse = memberService.getMemberById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("/members/projects/{id}")
  public ResponseEntity<ApiResponse> getAllMembersByProject(@PathVariable("id") long id) {
    ApiResponse apiResponse = projectService.getAllMembersByProject(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("/members")
  public ResponseEntity<ApiResponse> createMember(@RequestBody MemberRequest memberRequest) {
    ApiResponse apiResponse = memberService.createMember(memberRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PutMapping("/members/{id}")
  public ResponseEntity<ApiResponse> updateMember(@PathVariable("id") long id, @RequestBody MemberRequest memberRequest) {
    ApiResponse apiResponse = memberService.updateMember(id, memberRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @DeleteMapping("/members/{id}")
  public ResponseEntity<ApiResponse> deleteMemberById(@PathVariable("id") long id) {
    ApiResponse apiResponse = memberService.deleteMemberById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

}
