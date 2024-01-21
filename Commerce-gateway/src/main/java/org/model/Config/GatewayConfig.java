package org.model.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator (RouteLocatorBuilder builder,
                                      @Value("${model.customer.url}") String customerUrl,
                                      @Value("${model.order.url}") String orderUrl,
                                      @Value("${model.gateway.host}") String host) {
        return builder.routes()
                .route("customer_route", r -> r
                        .path("/customers/**")
                        .uri(customerUrl))
                .route("order_route", r -> r
                        .path("/orders/**")
                        .uri(orderUrl))
                .route("customer_id_route", r -> r
                        .path("/customers/{id}")
                        .uri(customerUrl))
                .route("order_id_route", r -> r
                        .path("/orders/{id}")
                        .uri(orderUrl))
                .route("fallback_route", r -> r
                        .path("/**")
                        .filters(f -> f.rewritePath("/api(?<remaining>.*)", "/$\\{remaining}"))
                        .uri(host))
                .build();
    }
}
