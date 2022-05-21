package com.management.cni.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

/*  @Autowired
  private com.management.cni.Service.AttachmentService attachmentService;

  @GetMapping("/{idProject}")
  public ResponseEntity<ApiResponse> getAllAttachmentsByProject(@PathVariable long idProject) {
    ApiResponse apiResponse = attachmentService.g(idProject);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("")
  public ResponseEntity<ApiResponse> createAttachment(@RequestBody AttachmentRequest AttachmentRequest) {
    ApiResponse apiResponse = attachmentService.createAttachment(AttachmentRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }*/
}
