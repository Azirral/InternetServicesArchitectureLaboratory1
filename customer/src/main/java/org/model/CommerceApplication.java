package org.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CommerceApplication {

	/**
	 * Application main entry point.
	 *
	 * @param args command line arguments
	 */

	public static void main(String[] args) {
		SpringApplication.run(CommerceApplication.class, args);
	}

	/**
	 * @param baseUrl base URL
	 * @return configured endpoint for character module
	 */
	@Bean
	public RestTemplate restTemplate(@Value("${model.user.url}") String baseUrl) {
		return new RestTemplateBuilder().rootUri(baseUrl).build();
	}
}