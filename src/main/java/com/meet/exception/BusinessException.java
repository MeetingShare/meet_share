package com.meet.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;

	public BusinessException() {
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message) {
		super(message);
		this.message=message;
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.message=message;
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
