package com.esports.manager.userManagement.exceptions;

public class UserAlreadyExistingException extends Exception{
	public UserAlreadyExistingException(String exMessage) {
		super(exMessage);
	}
}
