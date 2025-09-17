package com.ecommerce.rutamtb.repository;

import com.ecommerce.rutamtb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
