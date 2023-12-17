package org.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommerceApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${model.order.url}") String orderUrl,
			@Value("${model.customer.url}") String customerUrl,
			@Value("${model.gateway.host}") String host
	) {
		return builder
				.routes()
				.route("customers", route -> route
						.host(host)
						.and()
						.path(
								"/api/customers/{id}",
								"/api/customer/{id}",
								"/api/customers"
						)
						.uri(customerUrl)
				)
				.route("orders", route -> route
						.host(host)
						.and()
						.path(
								"/api/orders",
								"/api/orders/**",
								"/api/customers/{id}/orders",
								"/api/customers/{id}/orders/**"
						)
						.uri(orderUrl)
				)
				.build();
	}
}
