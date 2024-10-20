package com.kdk.app.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kdk.app.common.util.FileMimeTypeUtil;
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

	/**
	 * 동영상은 swagger ui에서 멈춤
	 * @param mode
	 * @param fileSeq
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/get-media")
	public ResponseEntity<?> getMedia(String mode, int fileSeq) throws IOException {
		String sMediaFileNm = "";

		switch (fileSeq) {
		case 1:
			sMediaFileNm = "istockphoto-1301592082-1024x1024.jpg";
			break;
		case 2:
			sMediaFileNm = "i-miss-the-rage-wav-194890.mp3";
			break;
		case 3:
			sMediaFileNm = "videoplayback.mp4";
			break;

		default:
			break;
		}

		ClassPathResource resource = new ClassPathResource("files/" + sMediaFileNm);
		File file = resource.getFile();
		Path path = file.toPath();

		byte[] byteFile = Files.readAllBytes(path);
		InputStream is = new BufferedInputStream(new FileInputStream(file));

		String sMimeType = FileMimeTypeUtil.getInstance().getFileMimeTypeTika(is);

		if ( "view".equalsIgnoreCase(mode) ) {
			return ResponseEntity.status(HttpStatus.OK)
				.header(HttpHeaders.CONTENT_TYPE, sMimeType)
				.body(byteFile);
		} else if ( "download".equalsIgnoreCase(mode) ) {
			return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + sMediaFileNm + "\"")
				.header(HttpHeaders.CONTENT_TYPE, sMimeType)
				.body(byteFile);
		} else {
			return ResponseEntity.badRequest().body("Invalid mode parameter");
		}
	}

}
