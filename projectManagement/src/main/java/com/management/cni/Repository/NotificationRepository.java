package com.management.cni.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.cni.Entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
