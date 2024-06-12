package com.kdk.config.mvc;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import dev.akkinoc.util.YamlResourceBundle;

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
public class MessageConfig {

    @Bean
    MessageSource messageSource() {
		//ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
    	YamlMessageSource source = new YamlMessageSource();

    	//source.setBasename("classpath:/messages/message");
		source.setBasename("messages/message");
		source.setDefaultEncoding(StandardCharsets.UTF_8.name());
		source.setCacheSeconds(60);
		source.setUseCodeAsDefaultMessage(true);

		return source;
	}

    @Bean
    CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver("lang");
		return localeResolver;
	}

}

class YamlMessageSource extends ResourceBundleMessageSource {

	@Override
	protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
		return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
	}

}
