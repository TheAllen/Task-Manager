package com.TheAllen.TaskManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TheAllen.TaskManager.domain.Backlog;
import com.TheAllen.TaskManager.domain.Project;
import com.TheAllen.TaskManager.exceptions.ProjectIDException;
import com.TheAllen.TaskManager.repositories.BacklogRepository;
import com.TheAllen.TaskManager.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	public ProjectRepository projectRepository;
	
	@Autowired
	public BacklogRepository backlogRepository;

	public Project saveOrUpdateProject(Project project) {

		try {
			project.setProject_identifier(project.getProject_identifier().toUpperCase());
			
			//Each time we create a project, we want a backlog that comes along with it
			//If not id, we create a backlog, else we're just updating a project
			if(project.getId()==null) {
				Backlog backlog = new Backlog();
				//set backlog and project to each other
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProject_identifier(project.getProject_identifier());
			}
			
			if(project.getId() != null) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProject_identifier()));
			}
			
			return projectRepository.save(project);
		} catch (Exception e) {
			throw new ProjectIDException("Project ID '" + project.getProject_identifier().toUpperCase()
					+ "' already exist in the Database.");
		}

	}

	public Project findProjectById(String projectID) {

		Project project = projectRepository.findByProjectIdentifier(projectID.toUpperCase());
		if (project == null) {
			throw new ProjectIDException("Project doesn't exist.");
		}

		return project;
	}

	public Iterable<Project> findAllProjects() {
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if(project == null) {
			throw new ProjectIDException("The project you are trying to delete does not exist.");
		}
		
		projectRepository.delete(project);
	}
	
	
}