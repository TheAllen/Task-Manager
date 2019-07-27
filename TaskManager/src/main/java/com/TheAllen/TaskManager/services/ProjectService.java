package com.TheAllen.TaskManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebRequestDataBinder;

import com.TheAllen.TaskManager.domain.Backlog;
import com.TheAllen.TaskManager.domain.Project;
import com.TheAllen.TaskManager.domain.User;
import com.TheAllen.TaskManager.exceptions.ProjectIDException;
import com.TheAllen.TaskManager.exceptions.ProjectNotFoundException;
import com.TheAllen.TaskManager.repositories.BacklogRepository;
import com.TheAllen.TaskManager.repositories.ProjectRepository;
import com.TheAllen.TaskManager.repositories.UserRepository;

@Service
public class ProjectService {

	@Autowired
	public ProjectRepository projectRepository;
	
	@Autowired
	public BacklogRepository backlogRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	public Project saveOrUpdateProject(Project project, String username) {
		
		//Project.getId != null
		//Find by db id
		if(project.getId() != null) {
			Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
			
			if(existingProject != null && (!existingProject.getProjectLeader().equals(username))) {
				throw new ProjectNotFoundException("Project not found in your account");
			}else if(existingProject == null) {
				throw new ProjectNotFoundException("Project with ID: " + project.getProjectIdentifier() + " cannot be updated because it doesn't exist.");
			}
		}

		try {
			
			User user = userRepository.findByUsername(username);
			
			//User to project relationship
			project.setUser(user);
			project.setProjectLeader(user.getUsername());
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

	public Project findProjectById(String projectID, String username) {

		Project project = projectRepository.findByProjectIdentifier(projectID.toUpperCase());
		
		if (project == null) {
			throw new ProjectIDException("Project doesn't exist.");
		}
		
		if(!project.getProjectLeader().equals(username)) {
			throw new ProjectNotFoundException("Project not found in your account.");
		}

		return project;
	}

	public Iterable<Project> findAllProjects(String username) {
		return projectRepository.findAllByProjectLeader(username);
	}
	
	public void deleteProjectByIdentifier(String projectId, String username) {
		
//		Project project = projectRepository.findByProjectIdentifier(projectId);
//		if(project == null) {
//			throw new ProjectIDException("The project you are trying to delete does not exist.");
//		}
		
		
		
		projectRepository.delete(findProjectById(projectId, username));
	}
	
	
}