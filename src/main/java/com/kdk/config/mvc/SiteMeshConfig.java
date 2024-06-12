package com.kdk.config.mvc;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

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
public class SiteMeshConfig extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder
		.addDecoratorPath("/*", "/defaultLayout.jsp")
		.addDecoratorPath("/test/layoutBase2", "/emptyLayout.jsp")
		.addExcludedPath("/index.jsp")
		.addExcludedPath("/test/login")
		.addExcludedPath("/test/main")
		.addExcludedPath("/test/i18n")
		.setMimeTypes("text/html");
	}

}
