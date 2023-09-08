package com.rest.api.exceptions;

public class ResouceNotFound extends RuntimeException {

	String resourceName;
	String fieldName;
	long fieldvalue;
	public ResouceNotFound(String resourceName, String fieldName, long fieldvalue) {
		super(String.format("%s not found with %s %s", resourceName, fieldName,fieldvalue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldvalue = fieldvalue;
	}
	
}
