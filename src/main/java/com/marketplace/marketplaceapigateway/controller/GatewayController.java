package com.marketplace.marketplaceapigateway.controller;

import com.marketplace.marketplaceapigateway.client.OrderClient;
import com.marketplace.marketplaceapigateway.client.ProductClient;
import com.marketplace.marketplaceapigateway.dto.OrderDto;
import com.marketplace.marketplaceapigateway.dto.ProductResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class GatewayController {
    private final ProductClient productClient;
    private final OrderClient orderClient;

    public GatewayController(ProductClient productClient, OrderClient orderClient) {
        this.productClient = productClient;
        this.orderClient = orderClient;
    }


    @QueryMapping
    @CircuitBreaker(name = "product-service", fallbackMethod = "productFallback")
    public List<ProductResponseDto> findAllProducts() {
        return productClient.findAllProducts();
    }

   @QueryMapping
    @CircuitBreaker(name = "order-service", fallbackMethod = "orderGetFallback")
    public List<OrderDto> getAllOrders(@Argument String role){

        return orderClient.getAllOrders(role);
    }
    @PostMapping("/orders")
    @CircuitBreaker(name = "order-service", fallbackMethod = "orderPostFallback")
    public OrderDto createOrder(
            @RequestHeader("X-Auth-User-Id") String userId,
            @RequestBody Object orderRequest) {

        return orderClient.createOrder(userId, orderRequest);
    }
    public List<ProductResponseDto> productFallback(Exception e) {
        return Collections.emptyList();
    }

    public List<OrderDto> orderGetFallback(Exception e) {
        return Collections.emptyList();
    }

    public OrderDto orderPostFallback(Exception e) {
        OrderDto errorDto = new OrderDto();
        return errorDto;
    }

}
