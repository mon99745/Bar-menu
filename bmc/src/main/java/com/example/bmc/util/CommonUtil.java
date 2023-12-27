package com.example.bmc.util;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class CommonUtil {

	/**
	 * 문자열을 스페이스로 결합
	 *
	 * @param values 문자열들
	 * @return 결합된 문자열
	 */
	public static String joiningBySpace(String... values) {
		return joining(" ", values);
	}

	/**
	 * 문자열을 구분자로 결합
	 *
	 * @param delimiter 구분자
	 * @param values    문자열들
	 * @return 결합된 문자열
	 */
	public static String joining(String delimiter, String... values) {
		return Arrays.stream(values)
				.filter(StringUtils::isNotEmpty)
				.collect(Collectors.joining(delimiter));
	}

	/**
	 * [code]message 형태로 반환
	 */
	public static String toCodeString(Object code, Object message) {
		return String.format("[%s]%s", code, message);
	}
}
