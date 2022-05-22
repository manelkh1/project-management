package com.management.cni.service;

import com.management.cni.entity.*;
import com.management.cni.repository.InviationRepository;
import com.management.cni.repository.ProjectRepository;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.ProjectRequest;
import com.management.cni.security.dto.response.MemberResponse;
import com.management.cni.security.dto.response.ProjectResponse;
import com.management.cni.security.mapper.MemberMapper;
import com.management.cni.security.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

  @Autowired
  ProjectRepository projectRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private InviationRepository inviationRepository;

  /// ONLY FOR ADMIN
  public ApiResponse getAllProjectsByAdmin() {
    User user = userService.getConnectedAdmin();
    List<ProjectResponse> projectResponses = new ArrayList<>();
    try {

      if (user != null) {
        List<Project> projects = projectRepository.findAll();
        if (!projects.isEmpty()) {
          for (Project project : projects) {
            ProjectResponse projectResponse = ProjectMapper.INSTANCE.convertToProjectResponse(project);
            projectResponses.add(projectResponse);
          }
        }
        return new ApiResponse(projectResponses, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  /// ONLY FOR MANAGER
  public ApiResponse getAllProjectsByManager() {
    User user = userService.getConnectedManager();
    List<ProjectResponse> projectResponses = new ArrayList<>();
    try {
      if (user != null) {
        List<Project> projects = projectRepository.findAllByManager(user.getManager());
        if (!projects.isEmpty()) {
          for (Project project : projects) {
            ProjectResponse projectResponse = ProjectMapper.INSTANCE.convertToProjectResponse(project);
            projectResponses.add(projectResponse);
          }
        }
        return new ApiResponse(projectResponses, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }
  /// ONLY FOR MEMBER
  public ApiResponse getAllProjectsByMember() {
    User user = userService.getConnectedMember();
    List<Invitation> invitations = new ArrayList<>();
    List<Project> projects = new ArrayList<>();
    List<ProjectResponse> projectResponses = new ArrayList<>();
    try {
      if (user != null) {
        invitations = inviationRepository.findInvitationByMemberAndStatus(user.getMember(), Status.ACCEPTED);
        for (Invitation invitation : invitations) {
          projects.add(invitation.getProject());
        }
        if (!projects.isEmpty()) {
          for (Project project : projects) {
            ProjectResponse projectResponse = ProjectMapper.INSTANCE.convertToProjectResponse(project);
            projectResponses.add(projectResponse);
          }
        }
        return new ApiResponse(projectResponses, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse getAllMembersByProject(long projectId) {
    Optional<Project> project = projectRepository.findById(projectId);
    User user = userService.getConnectedManager();
    List<Invitation> invitations = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    List<MemberResponse> memberResponses = new ArrayList<>();
    try {
      if (user != null) {
        invitations = inviationRepository.findInvitationByMemberAndStatusAndProject(user.getMember(), project.get(), Status.ACCEPTED);
        for (Invitation invitation : invitations) {
          members.add(invitation.getMember());
        }
        if (!members.isEmpty()) {
          for (Member member : members) {
            MemberResponse memberResponse = MemberMapper.INSTANCE.convertToMemberResponse(member);
            memberResponses.add(memberResponse);
          }
        }
        return new ApiResponse(memberResponses, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE MEMBER", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse getAllProjectsByBank() {
    User user = userService.getConnectedMember();
    List<Invitation> invitations = new ArrayList<>();
    List<Project> projects = new ArrayList<>();
    List<ProjectResponse> projectResponses = new ArrayList<>();
    try {
      if (user != null) {
        invitations = inviationRepository.findInvitationByManagerAndStatus(user.getManager(), Status.RECIEVED);
        for (Invitation invitation : invitations) {
          projects.add(invitation.getProject());
        }
        if (!projects.isEmpty()) {
          for (Project project : projects) {
            ProjectResponse projectResponse = ProjectMapper.INSTANCE.convertToProjectResponse(project);
            projectResponses.add(projectResponse);
          }
        }
        return new ApiResponse(projects, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse getProjectById(Long id) {
    User user = userService.getConnectedUser();
    try {
      if (user.getBank() != null || user.getAdmin() != null || user.getMember() != null || user.getManager() != null) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
          ProjectResponse projectResponse = ProjectMapper.INSTANCE.convertToProjectResponse(project.get());
          return new ApiResponse(projectResponse, null, HttpStatus.OK, LocalDateTime.now());
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

  public ApiResponse createProject(ProjectRequest projectRequest) {
    User user = userService.getConnectedManager();
    try {
      if (user != null) {
        Project project = ProjectMapper.INSTANCE.convertToProject(projectRequest);
        project.setManager(user.getManager());
        projectRepository.save(project);
        return new ApiResponse(null, "PROJECT CREATED", HttpStatus.OK, LocalDateTime.now());
     } else {
        return new ApiResponse(null, "USER MUST BE ADMIN", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse deleteProjectById(Long id) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
          projectRepository.deleteById(id);
          return new ApiResponse(null, "PROJECT DELETED", HttpStatus.OK, LocalDateTime.now());
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

  public ApiResponse updateProject(Long id, ProjectRequest projectRequest) {
    User user = userService.getConnectedAdmin();
    try {
      if (user != null) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
          Project newProject = project.get();
          newProject.setTitle(projectRequest.getTitle());
          newProject.setDescription(projectRequest.getDescription());
          newProject.setStatus(projectRequest.getStatus());
          projectRepository.save(newProject);
          return new ApiResponse(null, "PROJECT UPDATED", HttpStatus.OK, LocalDateTime.now());
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



/*	@Autowired
	private ProjectRepository projectRepository;

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
	//status user invitation Attachment

	//createProject(files,createProject) ProjectController
*//*	public Project createProject(MultipartFile[] files, CreateProject createProject) {
		//status scheduled
		Status statusProject = statusService.findStatusByStatusCode("SDL");
		//(projectdetails+users list)
		Project project = createProject.getProject();
		//modifier le status
		project.setStatus(statusProject);
		//set a user to the project (currentuser)
		//project.setUser(userService.currentUser());
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
				//dans "files" multipartfile[] enregistrer des documents(Attachment)
				for(int i=0; i<files.length; i++)
				{
					Attachment Attachment = new Attachment();
					Attachment.setName(StringUtils.cleanPath(files[i].getOriginalFilename()));
					//convert file to byte[]
					Attachment.setFileContent(files[i].getBytes());
					Attachment.setProject(it);
					//give the Attachment a subbmitted status
					Attachment.setStatus(statusInv);
					Attachment.setCreationDate(timestamp);
					attachmentRepository.save(Attachment);
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		});



		return savedProject.get();
	}
	*//*
	@Transactional
	public Optional<Project> getProjectById(Long projectId) {
		return projectRepository.findById(projectId);
	}

	@Transactional
	public List<Project> getProjectByUserId(Long userId){
	//	return projectRepository.getProjectByUserId( userId);
    return null;
	}

	@Transactional
	public List<Invitation> getMemberProject(Long projectId){
		Optional<Project> project = getProjectById(projectId);
		return invitationRepository.findInvitationByProject(project.get());
	}*/

}





