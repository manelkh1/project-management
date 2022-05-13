package com.management.cni.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.management.cni.Entity.Attachement;
import com.management.cni.Entity.Invitation;
import com.management.cni.Entity.Project;
import com.management.cni.Entity.Role;
import com.management.cni.Entity.Status;
import com.management.cni.Object.CreateProject;
import com.management.cni.Repository.ProjectRepository;
import com.management.cni.Service.AttachmentService;
import com.management.cni.Service.InvitationService;
import com.management.cni.Service.ProjectService;
import com.management.cni.Service.StatusService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService ;
	@Autowired 
	private ProjectRepository projectRepository;
	@Autowired
	private StatusService     statusService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private InvitationService invitationService;
	
	//on ne peut mettre consumes qu'avec requestmapping 
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"multipart/form-data"})
	 // A representation of an uploaded file received in a multipart request.MultipartFile.
	public Project addProject(@RequestPart("project") CreateProject createProject, @RequestPart("files") MultipartFile[] files){	
		return projectService.createProject(files, createProject);		
	}
	
	@GetMapping("/allProjects")
	public List<Project> getAllProjects(){
		return projectService.findAllProjects();
	}
	
	@GetMapping("/projectById/{projectId}")
	public Optional<Project> getProjectById(@PathVariable(value = "projectId") Long projectId) {
		return projectService.getProjectById(projectId);
	}
	
	@GetMapping("/attachments/{projectId}")
	public List<Attachement> getAttachmentsByProjectId(@PathVariable(value = "projectId") Long projectId) {
		return attachmentService.getAttachmentsByProjectId(projectId);
	}

	@GetMapping("/memberProject/{projectId}")
	public List<Invitation> getMembersProject(@PathVariable(value = "projectId") Long projectId){
		return projectService.getMemberProject(projectId);
	}
	
	@GetMapping("/invitationsProject")
	public List<Invitation> getInvitationsByUser(){
		return invitationService.getInvitationsByUser();
	}
	
	//@GetMapping("/acceptInv/{invitationId}")
	//public Invitation acceptInv(@PathVariable(value = "invitationId") Long invitationId){
		//return invitationService
	//}


}