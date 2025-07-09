package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration cfg, CorsRegistry cr) {
        cfg.setBasePath("/api");
        cfg.getExposureConfiguration()
           .withItemExposure((md, http) -> http.disable(HttpMethod.POST, HttpMethod.PUT,
                                                        HttpMethod.PATCH, HttpMethod.DELETE))
           .withCollectionExposure((md, http) -> http.disable(HttpMethod.POST, HttpMethod.PUT,
                                                              HttpMethod.PATCH, HttpMethod.DELETE));
    }
}
