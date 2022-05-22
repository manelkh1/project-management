package com.management.cni.controller;

import com.management.cni.entity.Attachment;
import com.management.cni.entity.Project;
import com.management.cni.entity.User;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.exceptions.MyFileNotFoundException;
import com.management.cni.repository.AttachmentRepository;
import com.management.cni.repository.ProjectRepository;
import com.management.cni.security.dto.response.AttachmentResponse;
import com.management.cni.security.mapper.AttachementMapper;
import com.management.cni.service.FileStorageService;
import com.management.cni.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

  private static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);

  @Autowired
  private FileStorageService fileStorageService;
  @Autowired
  UserService userService;
  @Autowired
  AttachmentRepository attachmentRepository;
  @Autowired
  ProjectRepository projectRepository;

  @GetMapping("")
  public ApiResponse getAllAttachments() {
    try {
      List<AttachmentResponse> attachmentResponses = new ArrayList<>();
      User member = userService.getConnectedMember();
      User manager = userService.getConnectedManager();
      if (member.getMember() != null 	|| member.getManager() != null ){
        List<Attachment> attachments = attachmentRepository.findAll();
        if (!attachments.isEmpty()) {
          for (Attachment attachment : attachments) {
            AttachmentResponse attachmentResponse = AttachementMapper.INSTANCE.convertToAttachmentResponse(attachment);
            attachmentResponses.add(attachmentResponse);
          }
          return new ApiResponse(attachmentResponses, null, HttpStatus.OK, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
    return null;
  }
  @GetMapping("/{id}")
  public ApiResponse getAttachmentById(long id) {
    try {
      User member = userService.getConnectedMember();
      User manager = userService.getConnectedManager();
      if (member.getMember() != null 	|| member.getManager() != null ){
        Optional<Attachment> attachment = attachmentRepository.findById(id);
        if (attachment.isPresent()) {
          AttachmentResponse attachmentResponse = AttachementMapper.INSTANCE.convertToAttachmentResponse(attachment.get());
          return new ApiResponse(attachmentResponse, null, HttpStatus.OK, LocalDateTime.now());
        } else {
          return new ApiResponse(null, "ATTACHMENT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  @GetMapping("/findByManager")
  public ApiResponse findByManager() {
    try {

      List<AttachmentResponse> attachmentResponses = new ArrayList<>();
      User user = userService.getConnectedUser();
      if (user != null && user.getManager() != null) {
        List<Attachment> attachments = attachmentRepository.findByManager(user.getManager());
        if (!attachments.isEmpty()) {
          for (Attachment attachment : attachments) {
            AttachmentResponse attachmentResponse = AttachementMapper.INSTANCE.convertToAttachmentResponse(attachment);
            attachmentResponses.add(attachmentResponse);
          }
          return new ApiResponse(attachmentResponses, null, HttpStatus.OK, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
      }
      return new ApiResponse(null, "NO CREATION", HttpStatus.NO_CONTENT, LocalDateTime.now());
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }
  @GetMapping("/findByMember")
  public ApiResponse findByMember() {
    try {

      List<AttachmentResponse> attachmentResponses = new ArrayList<>();
      User user = userService.getConnectedUser();
      if (user != null && user.getManager() != null) {
        List<Attachment> attachments = attachmentRepository.findByMember(user.getMember());
        if (!attachments.isEmpty()) {
          for (Attachment attachment : attachments) {
            AttachmentResponse attachmentResponse = AttachementMapper.INSTANCE.convertToAttachmentResponse(attachment);
            attachmentResponses.add(attachmentResponse);
          }
          return new ApiResponse(attachmentResponses, null, HttpStatus.OK, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
      }
      return new ApiResponse(null, "NO CREATION", HttpStatus.NO_CONTENT, LocalDateTime.now());
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

 /* @GetMapping("/findByProject")
  public ApiResponse findByProject() {
    try {

      List<AttachmentResponse> attachmentResponses = new ArrayList<>();
      User user = userService.getConnectedUser();
      if (user != null && user.getManager() != null) {
        List<Attachment> attachments = attachmentRepository.findByProject();
        if (!attachments.isEmpty()) {
          for (Attachment attachment : attachments) {
            AttachmentResponse attachmentResponse = AttachementMapper.INSTANCE.convertToAttachmentResponse(attachment);
            attachmentResponses.add(attachmentResponse);
          }
          return new ApiResponse(attachmentResponses, null, HttpStatus.OK, LocalDateTime.now());
        }
      } else {
        return new ApiResponse(null, "USER IS NOT AN STARTUPPER", HttpStatus.BAD_REQUEST, LocalDateTime.now());
      }
      return new ApiResponse(null, "NO CREATION", HttpStatus.NO_CONTENT, LocalDateTime.now());
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }
*/
  @PostMapping("/uploadFile/{projectId}")
  public ResponseEntity<ApiResponse> uploadFile(@RequestParam("file") MultipartFile file) {
    ApiResponse apiResponse = null;
    User user = userService.getConnectedUser();
   long  projectId = Long.parseLong("12");
    if (user.getMember() != null 	|| user.getManager() != null ) {
      String fileName = fileStorageService.storeFile(file);

      Project project = projectRepository.findById(projectId).get();
      if (project == null ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
      }
      String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
      Attachment attachment = new Attachment();
      attachment.setName(fileName);
      attachment.setFileDownloadUri(fileDownloadUri);
      attachment.setSize(file.getSize());
      attachment.setFileType(file.getContentType());
      if (user.getManager() != null) {
        attachment.setManager(user.getManager());
      }
      if (user.getMember() != null) {
        attachment.setMember(user.getMember());
      }
      attachment.setProject(project);
      attachmentRepository.save(attachment);
      AttachmentResponse fileResponse = AttachementMapper.INSTANCE.convertToAttachmentResponse(attachment);

      apiResponse = new ApiResponse(fileResponse, null, HttpStatus.OK, LocalDateTime.now());
      return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
          /*  return new UploadFileResponse(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());*/
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
  }

  @PostMapping("/uploadMultipleFiles")
  public ResponseEntity<ApiResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, long projectId) {
    ApiResponse apiResponse = null;
    List<AttachmentResponse> attachmentResponses = new ArrayList<>();
    User member = userService.getConnectedMember();
    User manager = userService.getConnectedManager();
    if (member.getMember() != null 	|| member.getManager() != null ) {
      for (MultipartFile file : files) {
        String fileName = fileStorageService.storeFile(file);
        Project project = projectRepository.findById(projectId).get();
        if (project == null ) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/").path(fileName).toUriString();
        Attachment attachment = new Attachment();
        attachment.setName(fileName);
        attachment.setFileDownloadUri(fileDownloadUri);
        attachment.setSize(file.getSize());
        attachment.setFileType(file.getContentType());
        if (member != null) {
          attachment.setManager(manager.getManager());
        }
        if (manager != null) {
          attachment.setMember(member.getMember());
        }
        attachment.setProject(project);

        AttachmentResponse attachmentResponse = AttachementMapper.INSTANCE.convertToAttachmentResponse(attachment);
        attachmentResponses.add(attachmentResponse);
        apiResponse = new ApiResponse(attachmentResponses, null, HttpStatus.OK, LocalDateTime.now());

      }
    }
      return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }


/*  @GetMapping("/downloadFile/{fileName:.+}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MyFileNotFoundException {
    // Load file as Resource
    Resource resource = fileStorageService.loadFileAsResource(fileName);

    // Try to determine file's content type
    String contentType = null;
    try {
      contentType = HttpServletRequest..getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      logger.info("Could not determine file type.");
    }

    // Fallback to the default content type if type could not be determined
    if (contentType == null) {
      contentType = "application/octet-stream";
    }

    return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
  }*/


}
