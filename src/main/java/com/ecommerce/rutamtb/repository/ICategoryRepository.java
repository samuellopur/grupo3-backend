package com.ecommerce.rutamtb.repository;

import com.ecommerce.rutamtb.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
