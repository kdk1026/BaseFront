package com.infoin.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

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
public class TilesConfig {

	// TilesConfigure
	@Bean
	public TilesConfigurer tilesConfigurer() {
		final TilesConfigurer configurer = new TilesConfigurer();

		final String[] sDefinitions = {"/WEB-INF/tiles/tiles.xml"};
		configurer.setDefinitions(sDefinitions);

		configurer.setCheckRefresh(true);

		return configurer;
	}

	// TilesViewResolver
	@Bean
	public TilesViewResolver tilesViewResolver() {
		final TilesViewResolver tilesViewResolver = new TilesViewResolver();

		tilesViewResolver.setViewClass(TilesView.class);
		tilesViewResolver.setOrder(1);

		return tilesViewResolver;
	}

}
