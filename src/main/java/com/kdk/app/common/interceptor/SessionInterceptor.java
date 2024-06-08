package com.kdk.app.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kdk.app.common.util.spring.SpringSessionUtil;
import com.kdk.app.common.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2024. 6. 8. 김대광	최초작성
 * </pre>
 *
 *
 * @author 김대광
 */
public class SessionInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String LOGIN_PAGE_URL = "/test/login";

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession(false);
		final String sCtxPath = request.getContextPath();

		if ( session != null ) {
			logger.debug("{}", session);
			UserVo userVo = (UserVo) SpringSessionUtil.getSessionLoginInfo();

			if ( userVo == null ) {
				response.sendRedirect(sCtxPath + LOGIN_PAGE_URL);
				return false;
			}
		} else {
			response.sendRedirect(sCtxPath + LOGIN_PAGE_URL);
			return false;
		}

		return true;
	}

}
