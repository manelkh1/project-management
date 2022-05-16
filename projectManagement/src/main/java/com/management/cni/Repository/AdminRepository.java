package com.management.cni.Repository;

import com.management.cni.Entity.Admin;
import com.management.cni.Entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
