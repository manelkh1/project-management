package com.management.cni.Repository;

import com.management.cni.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findUserByEmail(String email);

  //select users who are active enable
  @Query(value = "select * from t_user where status = true ;", nativeQuery = true)
  List<User> findAllUsers(boolean Status);

  //recherche
  @Query(value = "SELECT distinct  u.id, u.first_name, u.last_name, u.photo, u.post, u.email, u.password, u.status, u.city, u.country, u.code_postal,u.id_role FROM t_user u  WHERE u.status=true And ((lower(u.first_name) like  %:keyword% ) or (lower(u.last_name) like  %:keyword% ) or (lower(CONCAT(u.first_name, ' ', u.last_name)) like %:keyword% )) ;", nativeQuery = true)
  List<User> findAllMembers(String keyword);

}
