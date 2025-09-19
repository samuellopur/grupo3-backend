package com.ecommerce.rutamtb.controller;

import com.ecommerce.rutamtb.model.Imag;
import com.ecommerce.rutamtb.service.ImagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagController {

    private final ImagService imagService;

    @Autowired
    public ImagController(ImagService imagService) {
        this.imagService = imagService;
    }

    // Obtiene todas las imágenes
    @GetMapping
    public ResponseEntity<List<Imag>> getAllImages() {
        try {
            List<Imag> images = imagService.getAllImags();
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtiene una imagen por ID
    @GetMapping("/{id}")
    public ResponseEntity<Imag> getImageById(@PathVariable Long id) {
        try {
            Imag image = imagService.getImagById(id);
            if (image != null) {
                return ResponseEntity.ok(image);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crea una nueva imagen (metadata)
    @PostMapping
    public ResponseEntity<String> createImage(@RequestBody Imag image) {
        try {
            Imag savedImage = imagService.saveImag(image);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Image created successfully with ID: " + savedImage.getId_Imag());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating image: " + e.getMessage());
        }
    }

    // Sube una imagen (archivo)
  /*  @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam(value = "productId", required = false) Long productId,
                                              @RequestParam(value = "description", required = false) String description) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Please select a file to upload");
            }

            // Validar tipo de archivo
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Only image files are allowed");
            }

            // Crear objeto Imag con la información del archivo
            Imag image = new Imag();
            image.setNameImag(file.getOriginalFilename());
            image.setType(contentType);
            image.setSize(file.getSize());
            image.setDescription(Arrays.toString(file.getBytes())); // Si guardas el archivo como BLOB
            image.setDescription(description);
            // image.setProductId(productId); // Si tienes relación con Product

            Imag savedImage = imagService.saveImag(image);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Image uploaded successfully with ID: " + savedImage.getId_Imag());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error uploading image: " + e.getMessage());
        }
    }*/

    // Actualiza una imagen existente
    @PutMapping("/{id}")
    public ResponseEntity<String> updateImage(@PathVariable Long id, @RequestBody Imag image) {
        try {
            Imag existingImage = imagService.getImagById(id);
            if (existingImage != null) {
                image.setId_Imag(id); // Asegurar que el ID coincida
                imagService.saveImag(image);
                return ResponseEntity.ok("Image updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Image not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating image: " + e.getMessage());
        }
    }

    // Elimina una imagen por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) {
        try {
            Imag image = imagService.getImagById(id);
            if (image != null) {
                imagService.deleteImag(image);
                return ResponseEntity.ok("Image deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Image not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting image: " + e.getMessage());
        }
    }

    // Descarga una imagen por ID
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long id) {
        try {
            Imag image = imagService.getImagById(id);
            if (image != null && image.getDescription() != null) {
                return ResponseEntity.ok()
                        .header("Content-Type", image.getType())
                        .header("Content-Disposition", "attachment; filename=\"" + image.getNameImag() + "\"")
                        .body(image.getDescription().getBytes());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Endpoints adicionales específicos para imágenes (comentados)

    // Buscar imágenes por producto
    /*
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Imag>> getImagesByProductId(@PathVariable Long productId) {
        try {
            List<Imag> images = imagService.findByProductId(productId);
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Buscar imágenes por tipo
    /*
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Imag>> getImagesByType(@PathVariable String type) {
        try {
            List<Imag> images = imagService.findByType(type);
            return ResponseEntity.ok(images);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Obtener imagen principal de un producto
    /*
    @GetMapping("/product/{productId}/main")
    public ResponseEntity<Imag> getMainImageByProduct(@PathVariable Long productId) {
        try {
            Imag image = imagService.findMainImageByProductId(productId);
            if (image != null) {
                return ResponseEntity.ok(image);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Establecer imagen como principal
    /*
    @PutMapping("/{id}/set-main")
    public ResponseEntity<String> setAsMainImage(@PathVariable Long id, @RequestParam Long productId) {
        try {
            Imag image = imagService.getImagById(id);
            if (image != null) {
                imagService.setAsMainImage(id, productId);
                return ResponseEntity.ok("Image set as main successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Image not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error setting image as main: " + e.getMessage());
        }
    }
    */
}