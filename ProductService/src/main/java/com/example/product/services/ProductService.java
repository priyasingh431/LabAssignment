package com.example.product.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.product.clients.InventoryServiceClient;
//import com.example.product.configs.MyThreadLocalsHolder;
import com.example.product.entities.Product;
import com.example.product.models.ProductInventoryResponse;
import com.example.product.repositories.ProductRepository;

@Service
//@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ProductService {
	private final ProductRepository productRepository;
	private final InventoryServiceClient inventoryServiceClient;

	@Autowired
	public ProductService(ProductRepository productRepository, InventoryServiceClient inventoryServiceClient) {
		this.productRepository = productRepository;
		this.inventoryServiceClient = inventoryServiceClient;
	}

	public List<Product> findAllProducts() {
		List<Product> products = productRepository.findAll();
		final Map<String, Integer> inventoryLevels = getInventoryLevelsWithFeignClient();
		final List<Product> availableProducts = products.stream()
				.filter(p -> inventoryLevels.get(p.getCode()) != null && inventoryLevels.get(p.getCode()) > 0)
				.collect(Collectors.toList());
		return availableProducts;
	}

	private Map<String, Integer> getInventoryLevelsWithFeignClient() {
		Map<String, Integer> inventoryLevels = new HashMap<>();
		List<ProductInventoryResponse> inventory = inventoryServiceClient.getProductInventoryLevels();
		for (ProductInventoryResponse item : inventory) {
			inventoryLevels.put(item.getProductCode(), item.getAvailableQuantity());
		}
		return inventoryLevels;
	}

	public Optional<Product> findProductByCode(String code) {
		Optional<Product> productOptional = productRepository.findByCode(code);
		if (productOptional.isPresent()) {
			String correlationId = UUID.randomUUID().toString();
			//MyThreadLocalsHolder.setCorrelationId(correlationId);
			Optional<ProductInventoryResponse> itemResponseEntity = this.inventoryServiceClient
					.getProductInventoryByCode(code);
			if (itemResponseEntity.isPresent()) {
				Integer quantity = itemResponseEntity.get().getAvailableQuantity();
				productOptional.get().setInStock(quantity > 0);
			}
		}
		return productOptional;
	}
}
