package com.kdk.app.common.error;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2024. 11. 12. 김대광	최초작성
 * </pre>
 *
 *
 * @author 김대광
 */
@Controller
public class CustomErrorController implements ErrorController {

	private ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	@GetMapping("/error")
    public String handlError(WebRequest webRequest, Model model) {
    	Map<String, Object> errors = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
    	model.addAttribute("errors", errors);

    	Integer statusCode = (Integer) errors.get("status");

    	if ( statusCode == 404 ) {
    		return "error/404";
    	} else {
    		return "error/default";
    	}
    }

}
