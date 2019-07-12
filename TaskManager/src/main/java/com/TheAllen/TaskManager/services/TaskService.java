package com.TheAllen.TaskManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TheAllen.TaskManager.domain.Backlog;
import com.TheAllen.TaskManager.domain.Project;
import com.TheAllen.TaskManager.domain.ProjectTask;
import com.TheAllen.TaskManager.exceptions.ProjectNotFoundException;
import com.TheAllen.TaskManager.repositories.BacklogRepository;
import com.TheAllen.TaskManager.repositories.ProjectRepository;
import com.TheAllen.TaskManager.repositories.ProjectTaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository taskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		
		try {
			
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
			projectTask.setBacklog(backlog);
			
			Integer backlogSequence = backlog.getPTSequence();
			backlogSequence++;
			backlog.setPTSequence(backlogSequence);
			
			projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);
			
			
			if(projectTask.getStatus() == "" || projectTask.getStatus() == null) {
				projectTask.setStatus("TODO");
			}
			
			if(projectTask.getPriority() == null) {
				projectTask.setPriority(3);
			}
			
			return taskRepository.save(projectTask);
		}catch(Exception e) {
			throw new ProjectNotFoundException("Project not found");
		}
	}
	
	public Iterable<ProjectTask> findBacklogById(String backlog_id) {
		Project project = projectRepository.findByProjectIdentifier(backlog_id);
		if(project == null) {
			throw new ProjectNotFoundException("Project does not exist.");
		}
		return taskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
	}
}
