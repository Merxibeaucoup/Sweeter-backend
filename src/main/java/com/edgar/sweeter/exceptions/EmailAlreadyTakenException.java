package com.edgar.sweeter.exceptions;

public class EmailAlreadyTakenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public EmailAlreadyTakenException() {
		super("The Email provided is already taken");
	}

}
