package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.Product;
import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Product product);
}
