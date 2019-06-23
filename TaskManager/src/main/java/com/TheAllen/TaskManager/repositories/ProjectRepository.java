package com.TheAllen.TaskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TheAllen.TaskManager.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

	@Override
	Iterable<Project> findAllById(Iterable<Long> ids);
	
		
	@Override
	Iterable<Project> findAll();


	Project findByProjectIdentifier(String id);
	
	
}