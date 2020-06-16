package com.example.product.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.product.models.ProductInventoryResponse;



@FeignClient(name = "inventory-service")
public interface InventoryServiceFeignClient {

    @GetMapping("/ms-got/api/inventory")
    List<ProductInventoryResponse> getInventoryLevels();

    @GetMapping("/ms-got/api/inventory/{productCode}")
    List<ProductInventoryResponse> getInventoryByProductCode(String productCode);

}
