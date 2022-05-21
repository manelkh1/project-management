package com.management.cni.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "COMMENT")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long Id;

  @Column(name = "message")
  private String message;

  @Column(name = "time")
  private Timestamp time;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "manager_id", referencedColumnName = "id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Manager manager;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Member member;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_project", referencedColumnName = "id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Project project;

}
