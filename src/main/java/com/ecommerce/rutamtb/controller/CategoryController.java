package com.ecommerce.rutamtb.controller;

import com.ecommerce.rutamtb.model.Category;
import com.ecommerce.rutamtb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutamtb/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Obtiene todas las categorías
    @GetMapping("/searchall")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtiene una categoría por ID
    @GetMapping("/search/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            if (category != null) {
                return ResponseEntity.ok(category);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crea una nueva categoría
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        try {
            Category savedCategory = categoryService.saveCategory(category);
            if (savedCategory != null) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("Category created successfully with ID: " + savedCategory.getId_Category());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                        .body("Error creating category: saveCategory method not implemented in service");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating category: " + e.getMessage());
        }
    }

    // Actualiza una categoría existente
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category existingCategory = categoryService.getCategoryById(id);
            if (existingCategory != null) {
                category.setId_Category(id); // Asegurar que el ID coincida
                Category updatedCategory = categoryService.saveCategory(category);
                if (updatedCategory != null) {
                    return ResponseEntity.ok("Category updated successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                            .body("Error updating category: saveCategory method not implemented in service");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Category not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating category: " + e.getMessage());
        }
    }

    // Elimina una categoría por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            if (category != null) {
                categoryService.deleteCategory(category);
                return ResponseEntity.ok("Category deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Category not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting category: " + e.getMessage());
        }
    }

    // Endpoints adicionales específicos para categorías (comentados)

    // Buscar categorías por nombre
    /*
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategoriesByName(@RequestParam String name) {
        try {
            List<Category> categories = categoryService.findByNameContaining(name);
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Obtener productos de una categoría específica
    /*
    @GetMapping("/{id}/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long id) {
        try {
            List<Product> products = categoryService.getProductsByCategoryId(id);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */


}