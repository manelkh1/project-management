package com.management.cni.service;

import com.management.cni.entity.Comment;
import com.management.cni.entity.Project;
import com.management.cni.entity.User;
import com.management.cni.entity.UserRole;
import com.management.cni.repository.CommentRepository;
import com.management.cni.repository.ProjectRepository;
import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.CommentRequest;
import com.management.cni.security.dto.response.CommentResponse;
import com.management.cni.security.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

  @Autowired
  CommentRepository commentRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private ProjectRepository projectRepository;

  public ApiResponse getAllCommentsByProject(long idProject) {
    User user = userService.getConnectedUser();
    List<Comment> comments = new ArrayList<>();
    List<CommentResponse> commentResponses = new ArrayList<>();
    try {
      if (user.getUserRole().equals(UserRole.MANAGER) || user.getUserRole().equals(UserRole.MEMBER)) {
        Project project = projectRepository.getById(idProject);
        if (project == null) {
          return new ApiResponse(null, "PROJECT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        comments = commentRepository.findAllByProject(project);
        for (Comment comment : comments) {
          CommentResponse commentResponse = CommentMapper.INSTANCE.convertToCommentResponse(comment);
          if (comment.getManager() != null) {
            commentResponse.setSender(comment.getManager().getUser().getUsername());
          }
          if (comment.getMember() != null) {
            commentResponse.setSender(comment.getMember().getUser().getUsername());
          }
          commentResponses.add(commentResponse);
        }
        return new ApiResponse(commentResponses, null, HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE MEMBER OR MANAGER", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }

  public ApiResponse createComment(CommentRequest commentRequest) {
    User user = userService.getConnectedUser();
    try {
      if (user.getUserRole().equals(UserRole.MANAGER) || user.getUserRole().equals(UserRole.MEMBER)) {
        Project project = projectRepository.findById(commentRequest.getIdProject()).get();
        if (project == null) {
          return new ApiResponse(null, "PROJECT DOES NOT EXIST", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        }
        Comment comment = CommentMapper.INSTANCE.convertToComment(commentRequest);
        if (user.getMember() != null) {
          comment.setMember(user.getMember());
        } else if (user.getManager() != null) {
          comment.setManager(user.getManager());
        }
        comment.setProject(project);
        commentRepository.save(comment);
        return new ApiResponse(null, "COMMENT CREATED", HttpStatus.OK, LocalDateTime.now());
      } else {
        return new ApiResponse(null, "USER MUST BE MEMBER OR MANAGER", HttpStatus.UNAUTHORIZED, LocalDateTime.now());
      }
    } catch (Exception e) {
      return new ApiResponse(null, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }
  }





/*	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private InvitationService invitationService;
	@Autowired
	private AttachmentRepository attachmentRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private InviationRepository invitationRepository;


	@Transactional
	public Comment saveComment(Comment comment) {
		return  commentRepository.save(comment) ;
	}

	@Transactional
	public Comment addComment(@RequestBody Comment comment) {
		return comment;
	}

	@Transactional
	public List<Comment> findAllComments(){
		return commentRepository.findAll() ;
		}
	//status user invitation Attachment

	//createComment(files,createComment) CommentController
*//*	public Comment createComment(MultipartFile[] files, CreateComment createComment) {
		//status scheduled
		Status statusComment = statusService.findStatusByStatusCode("SDL");
		//(commentdetails+users list)
		Comment comment = createComment.getComment();
		//modifier le status
		comment.setStatus(statusComment);
		//set a user to the comment (currentuser)
		//comment.setUser(userService.currentUser());
		//getUser from the createComment object
		List<User> users = createComment.getUsers();
		Optional<Comment> savedComment = Optional.of(saveComment(comment));
		//status invitation est submitted apres createComment
		Status statusInv = statusService.findStatusByStatusCode("SUB");
		//TIMESTAMP stores values as year, month, day, hour, minute, second, and fractional seconds.
		Timestamp timestamp = Timestamp.from(Instant.now());
		//pour chaque projet ajouter (invitataion aux user)+(liste des documents)
		savedComment.ifPresent(it -> {
			try {
				// pour chaq user du projet ajouter une invitation
				users.forEach(u ->{
					Invitation invitation = new Invitation();
					invitation.setDate(timestamp);
					invitation.setStatus(statusInv);
					invitation.setComment(it);
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
					Attachment.setComment(it);
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



		return savedComment.get();
	}
	*//*
	@Transactional
	public Optional<Comment> getCommentById(Long commentId) {
		return commentRepository.findById(commentId);
	}

	@Transactional
	public List<Comment> getCommentByUserId(Long userId){
	//	return commentRepository.getCommentByUserId( userId);
    return null;
	}

	@Transactional
	public List<Invitation> getMemberComment(Long commentId){
		Optional<Comment> comment = getCommentById(commentId);
		return invitationRepository.findInvitationByComment(comment.get());
	}*/
}





