package com.example.bmc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * BMM 에러
 */

@Getter
@AllArgsConstructor
public enum BmmError implements Error {

	BMM_EMPTY_EXAMPLE(BmmError.CODE_PREFIX + "00-01", "에러 설명", HttpStatus.BAD_REQUEST);

	public static final String CODE_PREFIX = "BMM-";

	private final String code;
	private final String message;
	private final HttpStatus httpStatus;

	@Override
	public String toString() {
		return toCodeString();
	}

}
