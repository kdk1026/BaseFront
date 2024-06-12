package com.kdk.app.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kdk.app.common.util.spring.ContextUtil;
import com.kdk.app.common.util.spring.SpringSessionUtil;
import com.kdk.app.common.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;

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
@Controller
@RequestMapping("/test")
public class TestController {

	@GetMapping("/layoutBase")
	public ModelAndView tilesBase() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/test/layoutBase");
		return mav;
	}

	@GetMapping("/layoutBase2")
	public ModelAndView tilesBase2() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/test/layoutBase2");
		return mav;
	}

	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("test/auth/login");
		return mav;
	}

	@PostMapping("/loginProc")
	public ModelAndView loginProc(HttpServletResponse response, String id, String pw) {
		ModelAndView mav = new ModelAndView();

		if ( "test".equals(id) && "1234".equals(pw) ) {
			UserVo userVo = new UserVo();
			userVo.setUserId("test");
			userVo.setUserNm("홍길동");

			SpringSessionUtil.setSessionLoginInfo(userVo);

			mav.setViewName("redirect:/test/main");

		} else {
			mav.setViewName("redirect:/test/login");
		}

		return mav;
	}

	@GetMapping("/main")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();

		UserVo userVo = (UserVo) SpringSessionUtil.getSessionLoginInfo();
		mav.addObject("user", userVo);

		mav.setViewName("test/auth/main");
		return mav;
	}

	@PostMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mav = new ModelAndView();

		ContextUtil.getInstance().removeAttrFromSession(SpringSessionUtil.LOGIN_SESSION_ID);

		mav.setViewName("redirect:/test/login");
		return mav;
	}

	@GetMapping("/i18n")
	public ModelAndView i18n() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("test/i18n");
		return mav;
	}

}
