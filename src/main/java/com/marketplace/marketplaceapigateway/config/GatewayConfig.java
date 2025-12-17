package com.marketplace.marketplaceapigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()

                // Auth Service
                .route("auth_route", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8081"))

                // Product Service
                .route("product_route", r -> r.path("/api/products/**")
                        .uri("http://localhost:8082"))

                // Order Service
                .route("order_route", r -> r.path("/api/orders/**")
                        .uri("http://localhost:8084"))

                // Notification Service
                .route("notification_route", r -> r.path("/api/notifications/**")
                        .uri("http://localhost:8083"))

                // Payment Service
                .route("payment_route", r -> r.path("/api/payments/**")
                        .uri("http://localhost:8085"))

                .build();
    }
}
