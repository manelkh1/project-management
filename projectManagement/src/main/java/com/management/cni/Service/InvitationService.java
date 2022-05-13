package com.management.cni.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.cni.Entity.Invitation;
import com.management.cni.Entity.User;
import com.management.cni.Repository.InviationRepository;

@Service
public class InvitationService {
	@Autowired
	private InviationRepository inviationRepository;
	@Autowired
	private UserService userService;
	
	@Transactional
	public void addInvitation(Invitation invitation) {
		inviationRepository.save(invitation)
		;
	}
	
	@Transactional
	public List<Invitation> getInvitationsByUser(){
		User user = userService.currentUser();
		return inviationRepository.findInvitationByUser(user);
	}
	
	//@Transactional
	//public Invitation 

}
