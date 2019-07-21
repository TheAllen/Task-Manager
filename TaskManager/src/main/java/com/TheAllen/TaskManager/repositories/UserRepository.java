package com.TheAllen.TaskManager.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.TheAllen.TaskManager.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	
}
