package com.example.bmc.exception;

import com.example.bmc.util.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import com.example.bmc.util.CommonUtil;
import lombok.Getter;

/**
 * 기본 예외
 */
@Getter
public abstract class DefaultException
		extends RuntimeException {

	/**
	 * 기본 에러 코드 접두어
	 */
	public static final String CODE_PREFIX = "DEF-";

	protected final Error error;

	protected DefaultException(Error error, String message, Throwable cause) {
		super(CommonUtil.joiningBySpace(error.toCodeString(),
						StringUtils.isEmpty(message) && cause != null ? ExceptionUtil.getNotRootMessage(cause) : message),
				cause);
		this.error = error;
	}
}