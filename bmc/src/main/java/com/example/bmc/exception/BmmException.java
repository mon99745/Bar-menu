package com.example.bmc.exception;

/**
 * BMM 예외
 */
public class BmmException extends DefaultException{

	public BmmException(String message) {
		this(message, null);
	}

	public BmmException(Throwable cause) {
		this((String) null, cause);
	}
	public BmmException(Error error, String message) {
		this(error, message, null);
	}
	public BmmException(String message, Throwable cause) {
		this(Error.DefaultError.NONE, message, cause);
	}

	/**
	 * BMM 예외 생성자
	 *
	 * @param error   에러
	 * @param message 메세지
	 * @param cause   원인 예외
	 */
	public BmmException(Error error, String message, Throwable cause) {
		super(error, message, cause);
	}
}
