package com.infoin.config.mvc;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2023. 7. 31. kdk	최초작성
 * </pre>
 *
 *
 * @author kdk
 */
public class MessageConfig {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();

		source.setBasename("classpath:/messages/message");
		source.setDefaultEncoding(StandardCharsets.UTF_8.name());
		source.setCacheSeconds(60);
		source.setUseCodeAsDefaultMessage(true);

		return source;
	}

	@Bean
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setCookieName("lang");
		return localeResolver;
	}

}
