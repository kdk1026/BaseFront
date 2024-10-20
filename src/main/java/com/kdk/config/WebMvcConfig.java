package com.kdk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdk.app.common.interceptor.SessionInterceptor;
import com.kdk.config.mvc.HTMLCharacterEscapes;
import com.kdk.config.mvc.MessageConfig;

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
@Configuration
@Import({
	MessageConfig.class
})
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/index.jsp");
	}

    @Bean
    LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

    @Bean
    MappingJackson2HttpMessageConverter jsonEscapeConverter() {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
		objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
		return new MappingJackson2HttpMessageConverter(objectMapper);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor( this.localeChangeInterceptor() );

		registry.addInterceptor( new SessionInterceptor() )
			.addPathPatterns("/**")
			.excludePathPatterns("/",
					"/test/layoutBase", "/test/layoutBase2", "/test/login", "/test/loginProc", "/test/i18n", "/test/get-media",
					"/js/**", "/css/**");
	}

}
