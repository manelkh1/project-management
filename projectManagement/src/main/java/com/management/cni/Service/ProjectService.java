package com.management.cni.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.persistence.Id;
import javax.transaction.Transactional;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.management.cni.Entity.Attachement;
import com.management.cni.Entity.Invitation;
import com.management.cni.Entity.Project;
import com.management.cni.Entity.Status;
import com.management.cni.Entity.User;
import com.management.cni.Object.CreateProject;
import com.management.cni.Repository.AttachmentRepository;
import com.management.cni.Repository.InviationRepository;
import com.management.cni.Repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private StatusService statusService;
	@Autowired
	private InvitationService invitationService;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private InviationRepository invitationRepository;
	
	
	@Transactional
	public Project saveProject(Project project) {
		return  projectRepository.save(project) ;	
	}
	
	@Transactional
	public Project addProject(@RequestBody Project project) {
		return project;		
	}
	
	@Transactional
	public List<Project> findAllProjects(){
		return projectRepository.findAll() ;
		}
	//status user invitation attachement
	@Transactional
	//createProject(files,createProject) ProjectController
	public Project createProject(MultipartFile[] files, CreateProject createProject) {
		//status scheduled
		Status statusProject = statusService.findStatusByStatusCode("SDL");
		//(projectdetails+users list)
		Project project = createProject.getProject();
		//modifier le status
		project.setStatus(statusProject);
		//set a user to the project (currentuser)
		project.setUser(userService.currentUser());
		//getUser from the createProject object
		List<User> users = createProject.getUsers();
		Optional<Project> savedProject = Optional.of(saveProject(project));
		//status invitation est submitted apres createProject
		Status statusInv = statusService.findStatusByStatusCode("SUB");
		//TIMESTAMP stores values as year, month, day, hour, minute, second, and fractional seconds.
		Timestamp timestamp = Timestamp.from(Instant.now());
		//pour chaque projet ajouter (invitataion aux user)+(liste des documents)
		savedProject.ifPresent(it -> {
			try {
				// pour chaq user du projet ajouter une invitation
				users.forEach(u ->{
					Invitation invitation = new Invitation();
					invitation.setDate(timestamp);
					invitation.setStatus(statusInv);
					invitation.setProject(it);
					invitation.setUser(u);
					invitationService.addInvitation(invitation);
				});
				//dans "files" multipartfile[] enregistrer des documents(attachement)
				for(int i=0; i<files.length; i++) 
				{
					Attachement attachement = new Attachement();
					attachement.setName(StringUtils.cleanPath(files[i].getOriginalFilename()));
					//convert file to byte[]
					attachement.setFileContent(files[i].getBytes());
					attachement.setProject(it);
					//give the attachement a subbmitted status
					attachement.setStatus(statusInv);
					attachement.setCreationDate(timestamp);
					attachmentRepository.save(attachement);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		});
		
		
		
		return savedProject.get();
	}
	
	@Transactional
	public Optional<Project> getProjectById(Long projectId) {
		return projectRepository.findById(projectId);
	}
	
	@Transactional
	public List<Project> getProjectByUserId(Long userId){
		return projectRepository.getProjectByUserId( userId);
	}
	
	@Transactional
	public List<Invitation> getMemberProject(Long projectId){
		Optional<Project> project = getProjectById(projectId);
		return invitationRepository.findInvitationByProject(project.get());
	}

}





