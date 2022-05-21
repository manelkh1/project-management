package com.management.cni.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_Attachement" )
public class Attachement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="name")
	private String name ;

	@Column(name="creation_date")
	private Timestamp creationDate ;

  @Lob
	@Column(name="file_content")
	private byte[] fileContent ;

  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_project", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Project project;

  public Attachement() {
		super();
	}

  public Attachement( long id, String name, Timestamp creationDate, byte[] fileContent) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.fileContent = fileContent;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
