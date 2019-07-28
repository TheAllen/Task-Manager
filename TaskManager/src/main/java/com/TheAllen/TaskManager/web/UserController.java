package com.TheAllen.TaskManager.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TheAllen.TaskManager.domain.User;
import com.TheAllen.TaskManager.payload.JWTLoginSuccessResponse;
import com.TheAllen.TaskManager.payload.LoginRequest;
import com.TheAllen.TaskManager.security.JWTTokenProvider;
import com.TheAllen.TaskManager.security.SecurityConstants;
import com.TheAllen.TaskManager.services.MapValidationService;
import com.TheAllen.TaskManager.services.UserService;
import com.TheAllen.TaskManager.validator.UserValidator;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin
public class UserController {
	
	@Autowired
	private MapValidationService mapValidationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private JWTTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) return errorMap;
		
		Authentication auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword()
				)
		);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		String jwt = SecurityConstants.TOKEN_PREFIX + tokenProvider.generateToken(auth);
		
		return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
		userValidator.validate(user, result);
		ResponseEntity<?> errorMap = mapValidationService.mapValidationService(result);
		if(errorMap != null) return errorMap;
		
		User newUser = userService.saveUser(user);
		
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
}
