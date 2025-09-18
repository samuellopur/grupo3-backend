package com.ecommerce.rutamtb.service;

import com.ecommerce.rutamtb.model.Category;
import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category saveCategory(Category category);
    void deleteCategory(Category category);
}
