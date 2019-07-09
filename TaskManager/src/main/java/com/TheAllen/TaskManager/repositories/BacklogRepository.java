package com.TheAllen.TaskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TheAllen.TaskManager.domain.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long>{

}
