package com.management.cni.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.cni.Entity.Status;
import com.management.cni.Repository.StatusRepository;

@Service
public class StatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	
	public Status findStatusByStatusCode(String statusCode) {
		return statusRepository.findStatusByStatusCode(statusCode);
	}
}
