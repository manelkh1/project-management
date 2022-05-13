package com.management.cni.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.cni.Entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long > {

}
