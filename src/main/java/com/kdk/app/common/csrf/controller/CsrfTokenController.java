package com.kdk.app.common.csrf.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdk.app.common.CommonConstants;
import com.kdk.app.common.csrf.vo.CsrfTokenResVo;

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
@RestController
@RequestMapping("/csrf-token")
public class CsrfTokenController {

	@GetMapping()
	public ResponseEntity<CsrfTokenResVo> getCsrfToken(HttpServletRequest request, HttpServletResponse response) {
		CsrfTokenResVo resVo = new CsrfTokenResVo();

		String csrfToken = UUID.randomUUID().toString();

		HttpSession session = request.getSession();
		session.setAttribute(CommonConstants.CsrfToken.CSRF_TOKEN_SESSION_KEY, csrfToken);

		resVo.setCsrfToken(csrfToken);

		return ResponseEntity.status(HttpStatus.OK).body(resVo);
	}

}
