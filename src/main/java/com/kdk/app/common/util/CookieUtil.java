package com.kdk.app.common.util;

import java.util.Objects;

import com.kdk.app.common.ExceptionMessage;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2024. 6. 12. 김대광	최초작성
 * </pre>
 *
 *
 * @author 김대광
 */
public class CookieUtil {

	private CookieUtil() {
		super();
	}

	private static final String RESPONSE_IS_NUL = ExceptionMessage.isNull("response");
	private static final String REQUEST_IS_NUL = ExceptionMessage.isNull("request");
	private static final String COOKIE_NAME_IS_NUL = ExceptionMessage.isNull("cookieName");

	/**
	 * Servlet 3.0 쿠키 설정
	 * @param response
	 * @param name
	 * @param value
	 * @param expiry
	 * @param isUseJs
	 * @param isSecure
	 * @param domain
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int expiry, boolean isSecure, boolean isUseJs, String domain) {
		Objects.requireNonNull(response, RESPONSE_IS_NUL);
		Objects.requireNonNull(name, ExceptionMessage.isNull("name"));
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessage.isNull("name"));
		}

        Objects.requireNonNull(value, ExceptionMessage.isNull("value"));
        if (value.trim().isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessage.isNull("value"));
		}

		if ( expiry < 0 ) {
			throw new IllegalArgumentException(ExceptionMessage.isNegative("expiry"));
		}

		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(expiry);
		cookie.setPath("/");

		cookie.setSecure(isSecure);

		if (!isUseJs) {
			cookie.setHttpOnly(true);
		}

		if ( (domain != null) && (!domain.trim().isEmpty()) ) {
			cookie.setDomain(domain);
		}

		response.addCookie(cookie);
	}

	/**
	 * cookieName 인자 값을 가지는 쿠키 가져오기
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String cookieName) {
		Objects.requireNonNull(request, REQUEST_IS_NUL);
		Objects.requireNonNull(cookieName, COOKIE_NAME_IS_NUL);
		if (cookieName.trim().isEmpty()) {
			throw new IllegalArgumentException(COOKIE_NAME_IS_NUL);
		}

		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();

		if ( cookies != null ) {
			for (Cookie c : cookies) {
				if ( cookieName.equals(c.getName()) ) {
					cookie = c;
					break;
				}
			}
		}

		return cookie;
	}

	/**
	 * cookieName 인자 값을 가지는 쿠키의 값 가져오기
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Objects.requireNonNull(request, REQUEST_IS_NUL);
		Objects.requireNonNull(cookieName, COOKIE_NAME_IS_NUL);
		if (cookieName.trim().isEmpty()) {
			throw new IllegalArgumentException(COOKIE_NAME_IS_NUL);
		}

		Cookie cookie = getCookie(request, cookieName);
		return (cookie != null) ? cookie.getValue() : "";
	}

	/**
	 * 모든 쿠키 제거
	 * @param request
	 * @param response
	 */
	public static void removeCookies(HttpServletRequest request, HttpServletResponse response) {
		Objects.requireNonNull(request, REQUEST_IS_NUL);
		Objects.requireNonNull(response, RESPONSE_IS_NUL);

		Cookie[] cookies = request.getCookies();

		if (cookies != null && cookies.length > 0) {
			for (int i=0; i < cookies.length; i++) {
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);

				response.addCookie(cookies[i]);
			}
		}
	}

	/**
	 * 특정 쿠키 제거
	 * @param request
	 * @param response
	 * @param cookieName
	 * @param domain
	 */
	public static void removeCookie(HttpServletResponse response, String cookieName, String domain) {
		Objects.requireNonNull(response, RESPONSE_IS_NUL);
		Objects.requireNonNull(cookieName, COOKIE_NAME_IS_NUL);
		if (cookieName.trim().isEmpty()) {
			throw new IllegalArgumentException(COOKIE_NAME_IS_NUL);
		}

		Cookie cookie = new Cookie(cookieName, null);
		cookie.setPath("/");
		cookie.setMaxAge(0);

		if ( cookie.getSecure() ) {
			cookie.setSecure(true);
		}

		if ( cookie.isHttpOnly() ) {
			cookie.setHttpOnly(true);
		}

		if ( (domain != null) && (!domain.trim().isEmpty()) ) {
			cookie.setDomain(domain);
		}

		response.addCookie(cookie);
	}

	/**
	 * 쿠키 유무 확인
	 * @param request
	 * @param cookieName
	 */
	public static boolean isExist(HttpServletRequest request, String cookieName) {
		Objects.requireNonNull(request, REQUEST_IS_NUL);
		Objects.requireNonNull(cookieName, COOKIE_NAME_IS_NUL);
		if (cookieName.trim().isEmpty()) {
			throw new IllegalArgumentException(COOKIE_NAME_IS_NUL);
		}

		String cookieValue = getCookieValue(request, cookieName);
		return !"".equals(cookieValue);
	}

	/**
	 * 쿠키 유효기간 가져오기
	 * @param request
	 * @param cookieName
	 */
	public static int getCookieMaxAge(HttpServletRequest request, String cookieName) {
		Objects.requireNonNull(request, REQUEST_IS_NUL);
		Objects.requireNonNull(cookieName, COOKIE_NAME_IS_NUL);
		if (cookieName.trim().isEmpty()) {
			throw new IllegalArgumentException(COOKIE_NAME_IS_NUL);
		}

		Cookie cookie = getCookie(request, cookieName);
		return (cookie != null) ? cookie.getMaxAge() : 0;
	}

}