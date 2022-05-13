package com.management.cni.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.management.cni.Entity.Attachement;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachement, Long > {

	@Query(value = "SELECT * FROM t_attachement WHERE id_project = :projectId ;", nativeQuery = true)
	List<Attachement> getAttachmentsByProjectId(Long projectId);
}
