package com.example.product.repositories;



import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCode(String code);
}
