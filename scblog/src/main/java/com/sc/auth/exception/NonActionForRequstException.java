package com.sc.auth.exception;

@SuppressWarnings("serial")
public class NonActionForRequstException extends Exception {
	
	public NonActionForRequstException(String message){
		super(message);
		this.printExcep();
	}
	
	public void printExcep(){
		System.out.println(this.getMessage());
	}
}
