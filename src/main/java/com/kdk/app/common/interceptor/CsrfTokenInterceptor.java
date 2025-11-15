package com.kdk.app.common.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kdk.app.common.CommonConstants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
public class CsrfTokenInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession(false);

		String csrfToken = (String) session.getAttribute(CommonConstants.CsrfToken.CSRF_TOKEN_SESSION_KEY);
		String csrfTokenInParam = request.getParameter(CommonConstants.CsrfToken.CSRF_TOKEN_PARAM_KEY);

		if ( csrfToken == null || csrfTokenInParam == null || !csrfToken.equals(csrfTokenInParam) ) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name());
			return false;
		} else {
			return true;
		}
	}

}
