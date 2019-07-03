package com.TheAllen.TaskManager.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TheAllen.TaskManager.domain.Project;
import com.TheAllen.TaskManager.services.MapValidationService;
import com.TheAllen.TaskManager.services.ProjectService;

@RestController
@RequestMapping(path = "/api/project")
@CrossOrigin
public class ProjectController {

	@Autowired
	public ProjectService projectService;
	
	@Autowired
	public MapValidationService mapValidationService;

	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) return errorMap;
		
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
	}
	
	//@GetMapping("/{projectId}")
	@RequestMapping(path = "/{projectId}", method=RequestMethod.GET)
	public ResponseEntity<?> getProjectById(@PathVariable String projectId){
		Project project = projectService.findProjectById(projectId);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Project> getAllProjects() {
		return projectService.findAllProjects();
	}
	
	@RequestMapping(path="/{projectId}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
		projectService.deleteProjectByIdentifier(projectId);
		return new ResponseEntity<String>("Project with ID: " + projectId + " was deleted.", HttpStatus.OK);
	}
	

}