package com.dnb.accountservice.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dnb.accountservice.exceptions.IdNotFoundException;

@ControllerAdvice
public class AppAdvice {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity invalidAccountIdExceptionHandler(IdNotFoundException e) {
		
			Map<String, String> map = new HashMap<>();
			map.put("message", e.getMessage());
			map.put("HttpStatus", HttpStatus.NOT_FOUND+"");

			return new ResponseEntity(map,HttpStatus.NOT_FOUND);
	}

}
