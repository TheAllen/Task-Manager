package com.TheAllen.TaskManager.exceptions;

//Check DB
public class ProjectIdentifierException {
	
	private String projectIdentifier;

	public ProjectIdentifierException(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
}
