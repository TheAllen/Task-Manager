package com.TheAllen.TaskManager.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TheAllen.TaskManager.domain.ProjectTask;
import com.TheAllen.TaskManager.services.MapValidationService;
import com.TheAllen.TaskManager.services.TaskService;

@RestController
@RequestMapping(path="/api/backlog")
@CrossOrigin
public class BacklogController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private MapValidationService mapValidationService;
	
	@RequestMapping(path="/{backlog_id}", method=RequestMethod.POST)
	public ResponseEntity<?> addTaskToBacklog(@Valid @RequestBody ProjectTask projectTask,
			BindingResult result, @PathVariable String backlog_id, Principal principal) {
		
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) return errorMap;
		
		ProjectTask task = taskService.addProjectTask(backlog_id, projectTask, principal.getName());
		
		return new ResponseEntity<ProjectTask> (task, HttpStatus.CREATED);
	}
	
	@GetMapping("/{backlog_id}")
	public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id, Principal principal){
		
		return taskService.findBacklogById(backlog_id, principal.getName());
	}
	
	@GetMapping("/{backlog_id}/{projectTaskId}")
	public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String projectTaskId, Principal principal){
		
		ProjectTask task = taskService.findProjectTaskByProjectSequence(backlog_id, projectTaskId, principal.getName());
		return new ResponseEntity<ProjectTask>(task, HttpStatus.OK);
	}
	
	@PatchMapping("/{backlog_id}/{projectTaskId}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, @PathVariable String backlog_id, @PathVariable String projectTaskId, BindingResult result, Principal principal){
		
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) return errorMap;
		
		ProjectTask updatedProjectTask = taskService.updateProjectSequence(projectTask, backlog_id, projectTaskId, principal.getName());
		
		return new ResponseEntity<ProjectTask>(updatedProjectTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/{backlog_id}/{projectTaskId}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String projectTaskId, Principal principal) {
		taskService.deleteProjectTaskBySequence(backlog_id, projectTaskId, principal.getName());
		
		return new ResponseEntity<String>("Project Task: " + projectTaskId + " was successfully deleted", HttpStatus.OK);
	}
}
