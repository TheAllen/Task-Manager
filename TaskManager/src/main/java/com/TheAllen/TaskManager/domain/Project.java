package com.TheAllen.TaskManager.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Project name is required.")
	private String project_name;
	@NotBlank(message = "Project identifier is required.")
	@Size(min=4, max=5, message="Please use 4-5 characters")
	@Column(updatable=false, unique=true)
	private String projectIdentifier;
	@NotBlank(message = "Description can not be empty")
	private String description;
	
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date start_date;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date end_date;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date created_at;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date updated_at;
	
	public Project() {
		
	}
	
	@PrePersist
	protected void onCreated() {
		this.created_at = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_at = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getProject_identifier() {
		return projectIdentifier;
	}

	public void setProject_identifier(String project_identifier) {
		this.projectIdentifier = project_identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
	
}