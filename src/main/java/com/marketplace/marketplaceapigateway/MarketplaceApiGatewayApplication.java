package com.marketplace.marketplaceapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class MarketplaceApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketplaceApiGatewayApplication.class, args);
    }

}
