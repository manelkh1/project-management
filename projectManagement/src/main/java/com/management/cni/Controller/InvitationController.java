package com.management.cni.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.cni.Entity.Invitation;
import com.management.cni.Entity.Status;
import com.management.cni.Repository.InviationRepository;
import com.management.cni.Repository.StatusRepository;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController

public class InvitationController {
	@Autowired
    private StatusRepository statusRepository;
	@Autowired
    private InviationRepository invitationRepository;
	
	

	 

}
