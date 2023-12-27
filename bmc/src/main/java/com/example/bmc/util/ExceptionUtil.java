package com.example.bmc.util;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Optional;

public class ExceptionUtil {
	/**
	 * 원인 예외가 아니면 메세지를, 원인 예외 이면 ""를 반환
	 *
	 * @param e 예외
	 * @return 원인 예외가 아닌 메세지
	 */
	public static String getNotRootMessage(Throwable e) {
		return e == getRootCause(e) ? "" : e.getMessage();
	}

	/**
	 * 최상위 원인 예외를 반환
	 *
	 * @param e 예외
	 * @return 최상위 원인 예외
	 */
	public static Throwable getRootCause(Throwable e) {
		return Optional.ofNullable(ExceptionUtils.getRootCause(e))
				.orElse(e);
	}
}
