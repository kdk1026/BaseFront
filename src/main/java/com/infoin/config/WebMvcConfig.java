package com.infoin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoin.app.common.interceptor.JwtInterceptor;
import com.infoin.config.mvc.HTMLCharacterEscapes;
import com.infoin.config.mvc.MessageConfig;
import com.infoin.config.mvc.TilesConfig;

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
@Configuration
@Import({
	TilesConfig.class, MessageConfig.class
})
public class WebMvcConfig implements WebMvcConfigurer {

	@Value("${upload.folder}")
    private String uploadFolder;

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry)
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.allowedHeaders("*")
			.maxAge(3600);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/index.jsp");
	}

	@Bean
	public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
		objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());
		return new MappingJackson2HttpMessageConverter(objectMapper);
	}

	@Bean
	public JwtInterceptor jwtInterceptor() {
		return new JwtInterceptor();
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor( this.localeChangeInterceptor() );

		registry.addInterceptor( jwtInterceptor() )
			.addPathPatterns("/**")
			.excludePathPatterns("/test/**", "/js/**", "/css/**", "/images/**", "/fonts/**", "/img/**", "/upload/**", "/", "/init/**", "/login/**", "/file/download");
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/upload/**")
			.addResourceLocations(uploadFolder);
	}

}
