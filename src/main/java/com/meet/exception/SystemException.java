package com.meet.exception;

public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;

	private String code;

	public SystemException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public SystemException(String code,String message) {
		super(message);
		this.message = message;
		this.code = code;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public SystemException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SystemException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SystemException(String message) {
		super(message);
		this.message = message;
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}