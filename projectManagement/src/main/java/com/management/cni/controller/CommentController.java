package com.management.cni.controller;

import com.management.cni.exceptions.ApiResponse;
import com.management.cni.security.dto.request.CommentRequest;
import com.management.cni.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/comments")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @GetMapping("/{idProject}")
  public ResponseEntity<ApiResponse> getAllCommentsByProject(@PathVariable long idProject) {
    ApiResponse apiResponse = commentService.getAllCommentsByProject(idProject);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }

  @PostMapping("")
  public ResponseEntity<ApiResponse> createComment(@RequestBody CommentRequest commentRequest) {
    ApiResponse apiResponse = commentService.createComment(commentRequest);
    return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
  }


/*	@Autowired
	private CommentService commentService ;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private StatusService     statusService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private InvitationService invitationService;*/

  //on ne peut mettre consumes qu'avec requestmapping
  //@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = {"multipart/form-data"})
  // A representation of an uploaded file received in a multipart request.MultipartFile.
/*	public Comment addComment(@RequestPart("comment") CreateComment createComment, @RequestPart("files") MultipartFile[] files){
		return commentService.createComment(files, createComment);
	}*/
/*
	@GetMapping("/allComments")
	public List<Comment> getAllComments(){
		return commentService.findAllComments();
	}

	@GetMapping("/commentById/{commentId}")
	public Optional<Comment> getCommentById(@PathVariable(value = "commentId") Long commentId) {
		return commentService.getCommentById(commentId);
	}

	@GetMapping("/attachments/{commentId}")
	public List<Attachment> getAttachmentsByCommentId(@PathVariable(value = "commentId") Long commentId) {
		return attachmentService.getAttachmentsByCommentId(commentId);
	}

	@GetMapping("/memberComment/{commentId}")
	public List<Invitation> getMembersComment(@PathVariable(value = "commentId") Long commentId){
		return commentService.getMemberComment(commentId);
	}*/

	/*@GetMapping("/invitationsComment")
	public List<Invitation> getInvitationsByUser(){
		return invitationService.getInvitationsByUser();
	}*/

  //@GetMapping("/acceptInv/{invitationId}")
  //public Invitation acceptInv(@PathVariable(value = "invitationId") Long invitationId){
  //return invitationService
  //}
}
