package com.marketplace.marketplaceapigateway.client;

import com.marketplace.marketplaceapigateway.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "order-service", url = "http://localhost:8084/api/orders")
public interface OrderClient {
    @GetMapping
    List<OrderDto> getAllOrders(
            @RequestHeader(value = "X-Auth-User-Role", required = false) String role);

    @PostMapping
    OrderDto createOrder(
            @RequestHeader("X-Auth-User-Id") String userId,
            @RequestBody Object orderRequest);

    }
