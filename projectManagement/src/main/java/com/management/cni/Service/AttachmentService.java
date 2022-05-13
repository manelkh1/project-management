package com.management.cni.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.management.cni.Entity.Attachement;
import com.management.cni.Repository.AttachmentRepository;

@Service
public class AttachmentService {

	@Autowired
	private AttachmentRepository attachmentRepository;
	
	
	@Transactional
	public List<Attachement> getAttachmentsByProjectId(Long projectId){
		return attachmentRepository.getAttachmentsByProjectId(projectId);
	}
	
	
	//add file
	@Value("${upload.path}")
    private String uploadPath;

    public void save(MultipartFile file) throws FileUploadException {
        try {
            Path root = Paths.get(uploadPath);
            Path resolve = root.resolve(file.getOriginalFilename());
            if (resolve.toFile()
                       .exists()) {
                throw new FileUploadException("File already exists: " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
        }
    }
}
