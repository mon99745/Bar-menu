package com.example.bmc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * BMA 에러
 */

@Getter
@AllArgsConstructor
public enum BmaError implements Error {

	BMA_EMPTY_EXAMPLE(BmaError.CODE_PREFIX + "00-00", "에러 설명", HttpStatus.BAD_REQUEST),

	BMA_ID_NOT_EXIST(BmaError.CODE_PREFIX + "01-01", "존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST),
	BMA_ID_DUPLICATE(BmaError.CODE_PREFIX + "01-02", "중복된 아이디 입니다.", HttpStatus.BAD_REQUEST),
	BMA_ADMIN_INFO_NOT_EXIST(BmaError.CODE_PREFIX + "01-03", "게스트 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);



	public static final String CODE_PREFIX = "BMA-";

	private final String code;
	private final String message;
	private final HttpStatus httpStatus;

	@Override
	public String toString() {
		return toCodeString();
	}

}
