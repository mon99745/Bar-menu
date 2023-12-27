package com.example.bmc.vaildation;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 기본 설정
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultProperties {
	/**
	 * 설정 타이틀
	 */
	public static final String PROPERTY_PREFIX = "default";

	/**
	 * 설정 정보
	 */
	@Getter
	@Setter
	private static DefaultProperties instance = new DefaultProperties();

	static {
		instance.init();
	}

	/**
	 * 기본 HOST
	 */
	protected String baseHost = "http://localhost";

	/**
	 * 연결 초과 시간
	 */
	protected int connectTimeoutSec = 2;
	/**
	 * 응답 초과 시간
	 */
	protected int readTimeoutSec = 10;

	/**
	 * BMM 서버 관련 URL
	 */
	protected String bmmBaseUrl;
	protected String bmmBaseUri;
	protected String bmmReadUri;

	/**
	 * BMA 서버 관련 URL
	 */
	protected String bmaBaseUrl;
	protected String bmaBaseUri;

	/**
	 * 서버별 기본 URL 설정
	 */
	protected void init() {
		bmmBaseUrl = baseHost + ":8010";
		bmmBaseUri = bmmBaseUrl + "";
		bmmReadUri = bmmBaseUrl + "/auth/api/v1/userList";

		bmaBaseUrl = baseHost + ":8020";
		bmaBaseUri = bmaBaseUrl + "";
	}

}
