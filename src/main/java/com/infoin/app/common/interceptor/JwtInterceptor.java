package com.infoin.app.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoin.app.common.CommonConstants;
import com.infoin.app.common.jwt.JwtTokenProvider;
import com.infoin.app.common.util.RequestUtil;
import com.infoin.app.common.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2023. 2. 21. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

	private static final String LOGIN_PAGE = "/login/login";

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// ------------------------------------------------------------------------
		// 토큰 가져오기
		// ------------------------------------------------------------------------
		JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

		String sAccessToken = jwtTokenProvider.getTokenFromCookie(request);

		// ------------------------------------------------------------------------
		// 토큰 유효성 검증
		// ------------------------------------------------------------------------
		if ( StringUtils.isBlank(sAccessToken) ) {
			if ( "XMLHttpRequest".equals(request.getHeader("x-requested-with") ) ) {
				Map<String, Object> map = new HashMap<>();
				map.put("code", "logout");

				ObjectMapper mapper = new ObjectMapper();
				String sJsonStr = mapper.writeValueAsString(map);

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(sJsonStr);
				return false;
			}

			log.error("No AccessToken");
			response.sendRedirect(LOGIN_PAGE);
			return false;
		}

		int nValid = jwtTokenProvider.isValidateJwtToken(sAccessToken);

		switch (nValid) {
		case 0:
			log.error("AccessToken Invalid");
			break;
		case 2:
			log.error("AccessToken Expired");
			break;
		default:
			break;
		}

		if ( nValid != 1 ) {
			response.sendRedirect(LOGIN_PAGE);
			return false;
		}

		// ------------------------------------------------------------------------
		// 토큰에 이상이 없는 경우, access_token인지 검증
		// ------------------------------------------------------------------------
		String sTokenKind = jwtTokenProvider.getTokenKind(sAccessToken);

		if ( !CommonConstants.Jwt.ACCESS_TOKEN.equals(sTokenKind) ) {
			log.error("Is Not AccessToken");
			response.sendRedirect(LOGIN_PAGE);
			return false;
		}

		// ------------------------------------------------------------------------
		// 토큰에 이상이 없는 경우, 사용자 정보 추출하여 검증
		// ------------------------------------------------------------------------
		UserVo user = jwtTokenProvider.getAuthUserFromJwt(sAccessToken);

		if ( (user == null) || StringUtils.isBlank(user.getUserId()) ) {
			log.error("UserInfo Invalid");
			response.sendRedirect(LOGIN_PAGE);
			return false;
		}

		// ------------------------------------------------------------------------
		// 편하게 사용하기 위해, request에 담음
		// - 세션이 아니므로 addPathPatterns 대상 URI에서만 가지고 올 수 있음
		// ------------------------------------------------------------------------
		request.setAttribute("user", user);

		String sDomain = RequestUtil.getRequestDomain(request);
		request.setAttribute("domain", sDomain);

		String sLang = LocaleContextHolder.getLocale().toString();
		request.setAttribute("lang", sLang);

		return true;
	}

}
