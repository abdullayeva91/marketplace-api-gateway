package com.marketplace.marketplaceapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayClientConfig {

    @Bean
    public HttpGraphQlClient productClient() {
        return buildClient("http://localhost:8082/graphql");
    }

    @Bean
    public HttpGraphQlClient orderClient() {
        return buildClient("http://localhost:8084/graphql");
    }

    @Bean
    public HttpGraphQlClient paymentClient() {
        return buildClient("http://localhost:8085/graphql");
    }

    @Bean
    public HttpGraphQlClient notificationClient() {
        return buildClient("http://localhost:8083/graphql");
    }

    private HttpGraphQlClient buildClient(String url) {
        WebClient webClient = WebClient.builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return HttpGraphQlClient.builder(webClient).build();
    }
}