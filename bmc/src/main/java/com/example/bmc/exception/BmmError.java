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

	BMM_ID_NOT_EXIST(BmmError.CODE_PREFIX + "01-01", "존재하지 않는 아이디입니다.", HttpStatus.BAD_REQUEST),
	BMM_ID_DUPLICATE(BmmError.CODE_PREFIX + "01-02", "중복된 아이디 입니다.", HttpStatus.BAD_REQUEST),
	BMM_USER_INFO_NOT_EXIST(BmmError.CODE_PREFIX + "01-03", "회원 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
	BMM_GUEST_INFO_NOT_EXIST(BmmError.CODE_PREFIX + "01-04", "게스트 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),

	BMM_EMPTY_STOCK_QUANTITY(BmmError.CODE_PREFIX + "02-01", "해당 상품의 재고가 부족합니다.", HttpStatus.BAD_REQUEST),
	BMM_PRODUCT_ID_NOT_EXIST(BmmError.CODE_PREFIX + "02-02", "해당 상품이 없습니다.", HttpStatus.BAD_REQUEST),
	BMM_PRODUCT_INFO_NOT_EXIST(BmmError.CODE_PREFIX + "02-03", "해당 상품 정보를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),

	BMM_ORDER_ID_NOT_EXIST(BmmError.CODE_PREFIX + "03-01", "존재하지 않는 주문번호입니다.", HttpStatus.BAD_REQUEST),
	BMM_ORDER_AND_REQUESTER_NOT_MATCH(BmmError.CODE_PREFIX + "03-02", "주문자와 삭제 요청자가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);



	public static final String CODE_PREFIX = "BMM-";

	private final String code;
	private final String message;
	private final HttpStatus httpStatus;

	@Override
	public String toString() {
		return toCodeString();
	}

}
