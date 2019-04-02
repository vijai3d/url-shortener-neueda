package com.vijai.urlmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class UrlMinApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlMinApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/rest/v1/url/create").allowedOrigins("http://localhost:3000");
				registry.addMapping("/rest/v1/url/get").allowedOrigins("http://localhost:3000");

			}
		};
	}

}
