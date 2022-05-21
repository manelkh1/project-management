package com.management.cni.repository;

import java.util.List;

import com.management.cni.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long > {

	@Query(value = "SELECT * FROM t_attachement WHERE id_project = :projectId ;", nativeQuery = true)
	List<Attachment> getAttachmentsByProjectId(Long projectId);
}