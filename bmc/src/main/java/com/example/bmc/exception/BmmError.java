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

	BMM_EMPTY_EXAMPLE(BmmError.CODE_PREFIX + "00-00", "에러 설명", HttpStatus.BAD_REQUEST),

	BMM_EMPTY_STOCK_QUANTITY(BmmError.CODE_PREFIX + "00-01", "해당 상품의 재고가 부족합니다.해당 상품의 재고가 부족합니다.", HttpStatus.BAD_REQUEST);


	public static final String CODE_PREFIX = "BMM-";

	private final String code;
	private final String message;
	private final HttpStatus httpStatus;

	@Override
	public String toString() {
		return toCodeString();
	}

}
