package com.infoin.app.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.infoin.app.common.CommonConstants;
import com.infoin.app.common.CommonController;
import com.infoin.app.common.jwt.JwtTokenProvider;
import com.infoin.app.common.util.CookieUtil;
import com.infoin.app.common.util.PagingUtil;
import com.infoin.app.common.util.RequestUtil;
import com.infoin.app.common.util.spring.ContextUtil;
import com.infoin.app.common.util.spring.Spring4FileUtil;
import com.infoin.app.common.util.spring.Spring4FileUtil.FileVO;
import com.infoin.app.common.util.spring.SpringBootPropertyUtil;
import com.infoin.app.common.vo.UserVo;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2023. 1. 26. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
@Controller
@RequestMapping("/test")
public class TestController extends CommonController {

	@Value("${upload.folder}")
    private String uploadFolder;

	@GetMapping("/hello")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();

		HttpServletRequest request = ContextUtil.getInstance().getRequest();
		String sSiteDomain = RequestUtil.getRequestDomain(request);

		logger.debug("domain is {}", sSiteDomain);
		logger.debug("getProperty is {}", SpringBootPropertyUtil.getProperty("server.port"));

		mav.addObject("name", "abc");

		mav.setViewName("test/hello");
		return mav;
	}

	@GetMapping("/tilesBase")
	public ModelAndView tilesBase() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("base::test/tilesBase");
		return mav;
	}

	@GetMapping("/tilesNo")
	public ModelAndView tilesNo() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("no::test/tilesBase");
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
			UserVo user = new UserVo();
			user.setUserId("test");
			user.setUserNm("홍길동");

			JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
			String sToken = jwtTokenProvider.generateAccessToken(user);

			CookieUtil.addCookie(response, CommonConstants.Jwt.ACCESS_TOKEN, sToken, 60*60*24, false, false, null);

			mav.setViewName("redirect:/test/main");

		} else {
			mav.setViewName("redirect:/test/login");
		}

		return mav;
	}

	@GetMapping("/main")
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("test/auth/main");
		return mav;
	}

	@PostMapping("/logout")
	public ModelAndView logout() {
		ModelAndView mav = new ModelAndView();

		HttpServletResponse response = ContextUtil.getInstance().getResponse();
		CookieUtil.removeCookie(response, CommonConstants.Jwt.ACCESS_TOKEN);

		mav.setViewName("redirect:/test/login");
		return mav;
	}

	@GetMapping("/chart")
	public ModelAndView chart() {
		ModelAndView mav = new ModelAndView();

		JsonArray jArr = new JsonArray();
		JsonObject jObj = null;

		jObj = new JsonObject();
		jObj.addProperty("opNo", "공정01");
		jObj.addProperty("time", 40);
		jArr.add(jObj);

		jObj = new JsonObject();
		jObj.addProperty("opNo", "공정02");
		jObj.addProperty("time", 50);
		jArr.add(jObj);

		jObj = new JsonObject();
		jObj.addProperty("opNo", "공정03");
		jObj.addProperty("time", 35);
		jArr.add(jObj);

		jObj = new JsonObject();
		jObj.addProperty("opNo", "공정04");
		jObj.addProperty("time", 60);
		jArr.add(jObj);

		jObj = new JsonObject();
		jObj.addProperty("opNo", "공정05");
		jObj.addProperty("time", 55);
		jArr.add(jObj);

		mav.addObject("data", jArr);

		mav.setViewName("test/chart");
		return mav;
	}

	@GetMapping("/paging/{currentPage}")
	public ModelAndView paging(@PathVariable String currentPage) {
		ModelAndView mav = new ModelAndView();

		PagingUtil pagingUtil = new PagingUtil(5, 5, 110, currentPage, "");
		mav.addObject("paging", pagingUtil);

		mav.setViewName("test/paging");
		return mav;
	}

	@GetMapping("/inter")
	public ModelAndView inter() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("test/inter");
		return mav;
	}

	@GetMapping("/test")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("test/test");
		return mav;
	}

	@GetMapping("/file")
	public ModelAndView fileDown() {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("test/file");
		return mav;
	}

	@PostMapping("/fileDown")
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {
		FileVO fileVo = new FileVO();

		if ( super.getActiveProfile().equals("local") ) {
			fileVo.destFilePath = "/upload/test/";
		} else {
			fileVo.destFilePath = "/home/webdev/file_upload/Coea/test/";
		}

		fileVo.saveFileNm = "test.xlsx";

		Spring4FileUtil.downloadFile(fileVo, request, response);
	}

}
