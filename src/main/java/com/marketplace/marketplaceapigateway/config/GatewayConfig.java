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
                .route("auth_route", r -> r.path("/api/auth/**")
                        .uri("http://localhost:8081"))

                .route("product_route", r -> r.path("/api/products/**")
                        .filters(f -> f.rewritePath("/api/products/(?<segment>.*)", "/products/${segment}"))
                        .uri("http://localhost:8082"))

                .route("order_route", r -> r.path("/api/orders/**")
                        .filters(f -> f.rewritePath("/api/orders/(?<segment>.*)", "/orders/${segment}"))
                        .uri("http://localhost:8084"))

                .route("notification_route", r -> r.path("/api/notifications/**")
                        .filters(f -> f.rewritePath("/api/notifications/(?<segment>.*)", "/notifications/${segment}"))
                        .uri("http://localhost:8083"))

                .route("payment_route", r -> r.path("/api/payments/**")
                        .filters(f -> f.rewritePath("/api/payments/(?<segment>.*)", "/payments/${segment}"))
                        .uri("http://localhost:8085"))
                .build();
    }
}