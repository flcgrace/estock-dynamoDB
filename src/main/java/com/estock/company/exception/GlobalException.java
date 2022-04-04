package com.estock.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleCopanyNotFoundException(CompanyNotFoundException ex){
		ErrorObject eObj=new ErrorObject();
		eObj.setStatusCode(HttpStatus.NOT_FOUND.value());
		eObj.setMessage(ex.getMessage());
		eObj.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(eObj,HttpStatus.NOT_FOUND);
	}
}
