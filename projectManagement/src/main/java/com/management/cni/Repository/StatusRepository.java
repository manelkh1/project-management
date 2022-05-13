package com.management.cni.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.cni.Entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{
	
	Status findStatusByStatusCode(String statusCode);

}
