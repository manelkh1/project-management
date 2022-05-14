package com.management.cni.Repository;

import com.management.cni.Entity.Comment;
import com.management.cni.Entity.Manager;
import com.management.cni.Entity.Member;
import com.management.cni.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByMember(Member member);

  List<Comment> findAllByManager(Manager manager);

  List<Comment> findAllByProject(Project project);

}
