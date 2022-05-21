package com.management.cni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.cni.entity.Session;
import com.management.cni.entity.User;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
	Session findBytoken(String token);
	Session findByUser(User user);
}
