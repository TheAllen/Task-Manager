package com.TheAllen.TaskManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseException extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIDException(ProjectIDException ex, WebRequest request){
		ProjectIdentifierException exception = new ProjectIdentifierException(ex.getMessage());
		
		return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> projectNotFoundException(ProjectNotFoundException ex, WebRequest request){
		ProjectNotFoundExceptionResponse exception = new ProjectNotFoundExceptionResponse(ex.getMessage());
		
		return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistException ex, WebRequest request){
		UserAlreadyExistResponse exception = new UserAlreadyExistResponse(ex.getMessage());
		
		return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
	}
}
