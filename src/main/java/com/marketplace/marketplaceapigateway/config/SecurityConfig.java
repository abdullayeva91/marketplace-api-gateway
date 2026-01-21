package com.marketplace.marketplaceapigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable()) // WebSocket bağlantısı üçün CSRF mütləq bağlı olmalıdır
                .authorizeExchange(exchanges -> exchanges
                        // WebSocket yolunu hər kəs üçün açırıq
                        .pathMatchers("/ws-notification/**").permitAll()
                        // Auth yollarını açırıq
                        .pathMatchers("/api/auth/**").permitAll()
                        // Qalan hər şeyə hələlik icazə veririk
                        .anyExchange().permitAll()
                )
                .build();
    }

}