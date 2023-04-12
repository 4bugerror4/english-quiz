package com.bug.error.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.bug.error.entity.dto.CMRespDto;
import com.bug.error.handler.util.Script;

@RestController
@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> validationException(RuntimeException e) {
		
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String haveNoRandomProblems(IllegalArgumentException e) {
		
		return Script.back(e.getMessage());
	}
	
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public String haveNoMultipleRandomProblems(IndexOutOfBoundsException e) {
		
		return Script.back(e.getMessage());
	}
	

}
