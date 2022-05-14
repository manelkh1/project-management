package com.management.cni.Controller;

import com.management.cni.Entity.UploadResponseMessage;
import com.management.cni.Repository.ProjectRepository;
import com.management.cni.Service.AttachmentService;
import com.management.cni.service.ProjectService;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class AttachementController {

	@Autowired
	private ProjectService projectService ;
  @Autowired
	private ProjectRepository projectRepository;
  /*	@Autowired
    private StatusService     statusService;*/
	@Autowired
	private AttachmentService attachmentService;

  @PostMapping("/add")
    public ResponseEntity<UploadResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws FileUploadException {
        attachmentService.save(file);

        return ResponseEntity.status(HttpStatus.OK)
                             .body(new UploadResponseMessage("Uploaded the file successfully: " + file.getOriginalFilename()));
    }
}
