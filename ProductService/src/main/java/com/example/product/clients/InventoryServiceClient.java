package com.example.product.clients;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.product.models.ProductInventoryResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class InventoryServiceClient {
    //private final RestTemplate restTemplate;
	@Autowired
    private InventoryServiceFeignClient inventoryServiceFeignClient;
    //TODO; move this to config file
    private static final String INVENTORY_API_PATH = "http://inventory-service/ms-got/api/";


//    @Autowired
//    public InventoryServiceClient(RestTemplate restTemplate, InventoryServiceFeignClient inventoryServiceFeignClient) {
//        this.restTemplate = restTemplate;
//        this.inventoryServiceFeignClient = inventoryServiceFeignClient;
//    }

    @HystrixCommand(fallbackMethod = "getDefaultProductInventoryLevels",
            commandProperties = {
                 @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
            }
    )
    public List<ProductInventoryResponse> getProductInventoryLevels() {
        return this.inventoryServiceFeignClient.getInventoryLevels();
    }

    @SuppressWarnings("unused")
    List<ProductInventoryResponse> getDefaultProductInventoryLevels() {
        return new ArrayList<>();
    }

    @HystrixCommand(fallbackMethod = "getDefaultProductInventoryByCode")
    public Optional<ProductInventoryResponse> getProductInventoryByCode(String productCode)
    {
//        ResponseEntity<ProductInventoryResponse> itemResponseEntity =
//                restTemplate.getForEntity(INVENTORY_API_PATH + "inventory/{code}",
//                        ProductInventoryResponse.class,
//                        productCode);

    	return getDefaultProductInventoryByCode(productCode);
        /*
        //Simulate Delay
        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

//        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
//            int quantity = itemResponseEntity.getBody().getAvailableQuantity();
//           return Optional.ofNullable(itemResponseEntity.getBody());
//        } else {
//            return Optional.empty();
//        }
    }

    @SuppressWarnings("unused")
    Optional<ProductInventoryResponse> getDefaultProductInventoryByCode(String productCode) {
        ProductInventoryResponse response = new ProductInventoryResponse();
        response.setProductCode(productCode);
        response.setAvailableQuantity(50);
        return Optional.ofNullable(response);
    }

}
