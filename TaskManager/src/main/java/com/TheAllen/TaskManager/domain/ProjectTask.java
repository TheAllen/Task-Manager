package com.TheAllen.TaskManager.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProjectTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(updatable = false)
	private String projectSequence;
	@NotBlank(message = "Please include a project summary")
	private String summary;

	private String ac;
	private String status;
	private String priority;
	private Date dueDate;
	private Date createdAt;
	private Date updatedAt;
	
	//ManyToOne backlog
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name="backlog_id", updatable=false, nullable=false)
	@JsonIgnore
	private Backlog backlog;

	@Column(updatable = false)
	private String projectIdentifier;

	public ProjectTask() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectSequence() {
		return projectSequence;
	}

	public void setProjectSequence(String projectSequence) {
		this.projectSequence = projectSequence;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAc() {
		return ac;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	protected void onCreated() {
		createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	@Override
	public String toString() {
		return String.format(
				"ProjectTask [id=%s, projectSequence=%s, summary=%s, ac=%s, status=%s, priority=%s, dueDate=%s, createdAt=%s, updatedAt=%s, project_identifier=%s]",
				id, projectSequence, summary, ac, status, priority, dueDate, createdAt, updatedAt, projectIdentifier);
	}

}
