/**
 * 
 */
package com.example.demo.exceptionhandler;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.UserNotFoundException;


/**
 * 
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	/* @AfterThrowing(value = "execution(* *(..))", throwing = "e") */
	public Map<String, String> handleInvalid(MethodArgumentNotValidException e) {
		System.out.println("Error Occurred in handleInvalid Swati :-->");

		Map<String, String> errorMap = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleInvalidUser(UserNotFoundException e) {
		System.out.println("Error Occurred in handleInvalid Swati2 :-->"+e.getMessage());

		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage", e.getMessage());
		return errorMap;
	}
}