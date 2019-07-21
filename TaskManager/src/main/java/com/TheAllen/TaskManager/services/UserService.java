package com.TheAllen.TaskManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.TheAllen.TaskManager.domain.User;
import com.TheAllen.TaskManager.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User saveUser(User newUser) {
		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
		
		
		
		return userRepository.save(newUser);
	}
	
}
