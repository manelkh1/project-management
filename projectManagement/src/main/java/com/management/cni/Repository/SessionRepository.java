package com.management.cni.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.cni.Entity.Session;
import com.management.cni.Entity.User;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long>{
	Session findBytoken(String token);
	Session findByUser(User user);
}
