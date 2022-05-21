package com.management.cni.repository;

import com.management.cni.entity.Comment;
import com.management.cni.entity.Manager;
import com.management.cni.entity.Member;
import com.management.cni.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByMember(Member member);

  List<Comment> findAllByManager(Manager manager);

  List<Comment> findAllByProject(Project project);

}
