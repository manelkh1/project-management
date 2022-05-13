package com.management.cni.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.cni.Entity.NotificationMessage;

@Repository
public interface NotificationMessageRepository extends JpaRepository<NotificationMessage, Long>{

}
