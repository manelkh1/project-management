package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.ProjectRequest;
import com.management.cni.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @GetMapping("/projectsByAdmin")
  public ResponseEntity<ApiResponse> getAllProjectsByAdmin() {
    ApiResponse apiResponse = projectService.getAllProjectsByAdmin();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("/projectsByManager")
  public ResponseEntity<ApiResponse> getAllProjectsByManager() {
    ApiResponse apiResponse = projectService.getAllProjectsByManager();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("/projectsByMember")
  public ResponseEntity<ApiResponse> getAllProjectsByMember() {
    ApiResponse apiResponse = projectService.getAllProjectsByMember();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("/projectsByBank")
  public ResponseEntity<ApiResponse> getAllProjectsByBank() {
    ApiResponse apiResponse = projectService.getAllProjectsByBank();
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @GetMapping("/projects/{id}")
  public ResponseEntity<ApiResponse> getProjectById(@PathVariable("id") long id) {
    ApiResponse apiResponse = projectService.getProjectById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("/projects")
  public ResponseEntity<ApiResponse> createProject(@RequestBody ProjectRequest projectRequest) {
    ApiResponse apiResponse = projectService.createProject(projectRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PutMapping("/projects/{id}")
  public ResponseEntity<ApiResponse> updateProject(@PathVariable("id") long id, @RequestBody ProjectRequest projectRequest) {
    ApiResponse apiResponse = projectService.updateProject(id, projectRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @DeleteMapping("/projects/{id}")
  public ResponseEntity<ApiResponse> deleteProjectById(@PathVariable("id") long id) {
    ApiResponse apiResponse = projectService.deleteProjectById(id);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }





/*	@Autowired
	private ProjectService projectService ;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private StatusService     statusService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private InvitationService invitationService;*/

  //on ne peut mettre consumes qu'avec requestmapping
  //@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"multipart/form-data"})
  // A representation of an uploaded file received in a multipart request.MultipartFile.
/*	public Project addProject(@RequestPart("project") CreateProject createProject, @RequestPart("files") MultipartFile[] files){
		return projectService.createProject(files, createProject);
	}*/
/*
	@GetMapping("/allProjects")
	public List<Project> getAllProjects(){
		return projectService.findAllProjects();
	}

	@GetMapping("/projectById/{projectId}")
	public Optional<Project> getProjectById(@PathVariable(value = "projectId") Long projectId) {
		return projectService.getProjectById(projectId);
	}

	@GetMapping("/attachments/{projectId}")
	public List<Attachment> getAttachmentsByProjectId(@PathVariable(value = "projectId") Long projectId) {
		return attachmentService.getAttachmentsByProjectId(projectId);
	}

	@GetMapping("/memberProject/{projectId}")
	public List<Invitation> getMembersProject(@PathVariable(value = "projectId") Long projectId){
		return projectService.getMemberProject(projectId);
	}*/

	/*@GetMapping("/invitationsProject")
	public List<Invitation> getInvitationsByUser(){
		return invitationService.getInvitationsByUser();
	}*/

  //@GetMapping("/acceptInv/{invitationId}")
  //public Invitation acceptInv(@PathVariable(value = "invitationId") Long invitationId){
  //return invitationService
  //}
}
