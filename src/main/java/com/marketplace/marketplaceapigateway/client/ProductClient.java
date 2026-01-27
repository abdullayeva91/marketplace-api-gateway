package com.marketplace.marketplaceapigateway.client;

import com.marketplace.marketplaceapigateway.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8082/api/products")
public interface ProductClient {
    @GetMapping
     List <ProductResponseDto> findAllProducts();


}
