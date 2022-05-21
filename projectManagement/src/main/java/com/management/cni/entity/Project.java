package com.management.cni.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "project")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title")
  private String title;

  @Lob
  @Column(name = "description")
  private String description;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @Column(name = "creation_date")
  private Date creationDate;

  @Enumerated(EnumType.STRING)
  private Status status;

  /// created by
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "manager_id", referencedColumnName = "id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Manager manager;

  @JsonIgnore
  @OneToMany(mappedBy = "project")
  private List<Comment> comments;

  @JsonIgnore
  @OneToMany(mappedBy = "project")
  private List<Attachment> Attachments;

  @JsonIgnore
  @OneToMany(mappedBy = "project")
  private List<Invitation> invitations;

 /* @JsonIgnore
  @OneToMany(mappedBy = "project")
  private List<Member> members;*/

  @ManyToMany
  Set<Member> members;
}
