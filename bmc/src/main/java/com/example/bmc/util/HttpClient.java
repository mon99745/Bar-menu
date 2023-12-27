package com.example.bmc.util;

import com.example.bmc.vaildation.DefaultProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import java.io.IOException;
import java.net.CookieManager;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * HTTP 요청 클라이언트
 */
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpClient {
	/**
	 * OkHttpClient
	 */
	private static OkHttpClient instance;

	/**
	 * OkHttpClient 반환
	 *
	 * @return OkHttpClient
	 */
	private static OkHttpClient getInstance() {
		if (instance == null) {
			X509TrustManager x509TrustManager = new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) {
				} //NOSONAR HTTP 클라이언트 용으로만 사용

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) {
				} //NOSONAR HTTP 클라이언트 용으로만 사용

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0]; //NOSONAR HTTP 클라이언트 용으로만 사용
				}
			};

			SSLContext sslContext;
			try {
				sslContext = SSLContexts.custom()
						.loadTrustMaterial(null, new TrustSelfSignedStrategy())
						.build();
			} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
				throw new RuntimeException("HTTP 클라이언트 초기화를 실패했습니다.", e);
			}

			ConnectionPool okHttpConnectionPool = new ConnectionPool(200, 60, TimeUnit.SECONDS);
			instance = new OkHttpClient.Builder()
					.cookieJar(new JavaNetCookieJar(new CookieManager()))
					.connectionPool(okHttpConnectionPool)
					.sslSocketFactory(sslContext.getSocketFactory(), x509TrustManager)
					.hostnameVerifier((hostname, session) -> true)
					.retryOnConnectionFailure(false)
					.connectTimeout(DefaultProperties.getInstance().getConnectTimeoutSec(), TimeUnit.SECONDS)
					.readTimeout(DefaultProperties.getInstance().getReadTimeoutSec(), TimeUnit.SECONDS)
					.build();
		}
		return instance;
	}

	/**
	 * HTTP GET 메소드 요청
	 *
	 * @param url URL
	 * @return 응답 메세지
	 */
	public static String sendGet(String url) {
		return send("GET", url, null);
	}

	/**
	 * HTTP POST 메소드 요청
	 *
	 * @param url         URL
	 * @param requestBody Body
	 * @return 응답 메세지
	 */
	public static String sendPost(String url, String requestBody) {
		return send("POST", url, requestBody);
	}

	/**
	 * HTTP PUT 메소드 요청
	 *
	 * @param url         URL
	 * @param requestBody Body
	 * @return 응답 메세지
	 */
	public static String sendPut(String url, String requestBody) {
		return send("PUT", url, requestBody);
	}

	/**
	 * HTTP DELETE 메소드 요청
	 *
	 * @param url         URL
	 * @param requestBody Body
	 * @return 응답 메세지
	 */
	public static String sendDelete(String url, String requestBody) {
		return send("DELETE", url, requestBody);
	}

	/**
	 * HTTP 요청
	 *
	 * @param method      메소드
	 * @param url         URL
	 * @param requestBody 요청 본문
	 * @return 응답 본문
	 */
	private static String send(String method, String url, String requestBody) {
		// 요청문 생성
		RequestBody reqBody = null;
		if (StringUtils.isNotEmpty(requestBody)) {
			reqBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), requestBody);
		}

		Request request = new Request.Builder()
				.url(url)
				.method(method.toUpperCase(), reqBody)
				.build();

		return send(request);
	}

	/**
	 * HTTP 요청
	 *
	 * @param request okhttp.request
	 * @return okhttp.response
	 */
	public static String send(Request request) {
		String message = String.format(" request: [%s %s]%s", request.method(), request.url(), request.body());
		log.debug(message);

		try (Response response = getInstance().newCall(request).execute()) {
			String responseBody = null;
			ResponseBody resBody = response.body();
			if (resBody != null) {
				responseBody = resBody.string();
			}

			// 에러 핸들링
			if (response.isSuccessful()) {
				return responseBody;
			} else {
				throw new RuntimeException("HTTP 응답을 실패했습니다." + String.format(" %s response: %s",
						message, CommonUtil.toCodeString(response.code(), responseBody)));
			}
		} catch (IOException e) {
			throw new RuntimeException("HTTP 요청을 실패했습니다." + message, e);
		}
	}
}
