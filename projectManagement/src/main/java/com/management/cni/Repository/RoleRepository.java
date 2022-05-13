package com.management.cni.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.cni.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
