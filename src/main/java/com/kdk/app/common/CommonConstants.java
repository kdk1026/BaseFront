package com.kdk.app.common;

import java.nio.charset.StandardCharsets;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2025. 11. 14. 김대광	최초작성
 * </pre>
 *
 *
 * @author 김대광
 */
public class CommonConstants {

	private CommonConstants() {
		super();
	}

	// ------------------------------------------------------------------------
	// 인코딩
	// ------------------------------------------------------------------------
	public static class Encoding {
		private Encoding() {
			super();
		}

		public static final String UTF_8 = StandardCharsets.UTF_8.toString();
	}

	// ------------------------------------------------------------------------
	// 프로파일
	// ------------------------------------------------------------------------
	public static class Profile {
		private Profile() {
			super();
		}

		public static final String LOCAL = "local";
		public static final String DEV = "dev";
		public static final String PROD = "prod";
	}

	// ------------------------------------------------------------------------
	// CSRF Token
	// ------------------------------------------------------------------------
	public static class CsrfToken {
		private CsrfToken() {

		}

		public static final String CSRF_TOKEN_SESSION_KEY = "XSRF-TOKEN";
		public static final String CSRF_TOKEN_PARAM_KEY = "X-CSRF-TOKEN";
	}

}
