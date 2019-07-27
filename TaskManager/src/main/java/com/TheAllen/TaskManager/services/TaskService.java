package com.TheAllen.TaskManager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private ProjectService projectService;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {
		
		
		//Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		Backlog backlog = projectService.findProjectById(projectIdentifier, username).getBacklog();
		projectTask.setBacklog(backlog);
		
		Integer backlogSequence = backlog.getPTSequence();
		backlogSequence++;
		backlog.setPTSequence(backlogSequence);
		
		projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);
		
		
		if(projectTask.getStatus() == "" || projectTask.getStatus() == null) {
			projectTask.setStatus("TODO");
		}
		
		if(projectTask.getPriority() == null || projectTask.getPriority() == 0) {
			projectTask.setPriority(3);
		}
		
		return taskRepository.save(projectTask);
		
	}
	
	public Iterable<ProjectTask> findBacklogById(String backlog_id, String username) {
		
//		Project project = projectRepository.findByProjectIdentifier(backlog_id);
//		
//		if(project == null) {
//			throw new ProjectNotFoundException("Project does not exist.");
//		}
		
		projectService.findProjectById(backlog_id, username);
		
		return taskRepository.findByProjectIdentifierOrderByPriority(backlog_id);
	}
	
	public ProjectTask findProjectTaskByProjectSequence(String backlog_id, String sequence) {
		
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog == null) {
			throw new ProjectNotFoundException("Project not found with that backlog id");
		}
		
		ProjectTask projectTask = taskRepository.findByProjectSequence(sequence);
		if(projectTask == null) {
			throw new ProjectNotFoundException("Project Task: " + sequence + " not found.");
		}
		
		if(!projectTask.getProjectIdentifier().equals(backlog.getProject_identifier())) {
			throw new ProjectNotFoundException("Project task id and backlog id doesn't match.");
		}
		
		return projectTask;
	}
	
	public ProjectTask updateProjectSequence(ProjectTask updatedTask, String backlog_id) {
		ProjectTask projectTask = taskRepository.findByProjectSequence(updatedTask.getProjectSequence());
		projectTask = updatedTask;
		return taskRepository.save(projectTask);
	}
	
	public void deleteProjectTaskBySequence(String backlog_id, String sequence) {
		ProjectTask projectTask = findProjectTaskByProjectSequence(backlog_id, sequence);
		
//		List<ProjectTask> list = projectTask.getBacklog().getTasks();
//		list.remove(projectTask);
//		backlogRepository.save(projectTask.getBacklog());
		
		taskRepository.delete(projectTask);
	}
}
