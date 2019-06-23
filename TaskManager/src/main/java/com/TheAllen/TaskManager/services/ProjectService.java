package com.TheAllen.TaskManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TheAllen.TaskManager.domain.Project;
import com.TheAllen.TaskManager.exceptions.ProjectIDException;
import com.TheAllen.TaskManager.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProject_identifier(project.getProject_identifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception e) {
			throw new ProjectIDException("Project ID '" + project.getProject_identifier().toUpperCase() + "' already exist in the Database.");
		}
		
		
	}
	
	public Project findProjectById(String projectID) {
		return projectRepository.findByProjectIdentifier(projectID);
	}
}