package com.example.bmc.exception;

import com.example.bmc.util.CommonUtil;
import com.example.bmc.util.SpringUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 최상위 에러 유형
 */
public interface Error extends Serializable {

	/**
	 * 에러 유형을 반환
	 */
	String name();

	/**
	 * 에러 코드를 반환
	 */
	String getCode();

	/**
	 * 에러 메세지를 반환
	 */
	String getMessage();

	/**
	 * HTTP 상태 코드를 반환
	 */
	HttpStatus getHttpStatus();

	default String ofString() {
		return String.format("name: %s code: %s message: %s httpStatus: %s",
				name(), getCode(), getMessage(), getHttpStatus());
	}

	default String toCodeString() {
//		return StringUtil.toCodeString(name(), getMessage());
		String name = "".equals(name()) || DefaultError.NONE.name().equals(name()) ? "" : "][" + name();
//		String serverInfo = SpringUtil.getServerInfo(); // ex) localhost:8010
		String serverInfo = SpringUtil.getEnvironment() // ex) bmm
				.map(a -> a.getProperty(SpringUtil.SPRING_CONFIG_NAME))
				.orElse("");
		return CommonUtil.toCodeString(serverInfo + name, getMessage()); // error
	}

	/**
	 * 기본 에러 유형
	 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	enum DefaultError
			implements Error {
		NONE(DefaultException.CODE_PREFIX + "10-01", "", HttpStatus.INTERNAL_SERVER_ERROR),
		UNKNOWN(DefaultException.CODE_PREFIX + "10-02", "알수 없음", HttpStatus.INTERNAL_SERVER_ERROR);

		private final String code;
		private final String message;
		private final HttpStatus httpStatus;

		@Override
		public String toString() {
			return toCodeString();
		}
	}

	/**
	 * 커스텀 에러 유형
	 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PUBLIC)
	class CustomError
			implements Error {
		public static CustomError createError(Error error, HttpStatus httpStatus) {
			return createError(error.name(), error.getCode(), error.getMessage(), httpStatus);
		}

		public static CustomError createError(String name, String code, String message, HttpStatus httpStatus) {
			return new CustomError(name, code, message, httpStatus);
		}

		protected final String name;
		protected final String code;
		protected final String message;
		protected final HttpStatus httpStatus;

		@Override
		public String name() {
			return name;
		}

		@Override
		public String toString() {
			return toCodeString();
		}
	}
}
