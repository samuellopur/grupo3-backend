package com.ecommerce.rutamtb.controller;

import com.ecommerce.rutamtb.model.OrderDetail;
import com.ecommerce.rutamtb.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    // Obtiene todos los detalles de órdenes
    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        try {
            List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
            return ResponseEntity.ok(orderDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtiene un detalle de orden por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable Long id) {
        try {
            OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
            if (orderDetail != null) {
                return ResponseEntity.ok(orderDetail);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crea un nuevo detalle de orden
    @PostMapping
    public ResponseEntity<String> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        try {
            OrderDetail savedOrderDetail = orderDetailService.saveOrderDetail(orderDetail);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Order detail created successfully with ID: " + savedOrderDetail.getId_OrderDetail());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating order detail: " + e.getMessage());
        }
    }

    // Actualiza un detalle de orden existente
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        try {
            OrderDetail existingOrderDetail = orderDetailService.getOrderDetailById(id);
            if (existingOrderDetail != null) {
                orderDetail.setId_OrderDetail(id); // Asegurar que el ID coincida
                orderDetailService.saveOrderDetail(orderDetail);
                return ResponseEntity.ok("Order detail updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Order detail not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating order detail: " + e.getMessage());
        }
    }

    // Elimina un detalle de orden por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable Long id) {
        try {
            OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
            if (orderDetail != null) {
                orderDetailService.deleteOrderDetail(orderDetail);
                return ResponseEntity.ok("Order detail deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Order detail not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting order detail: " + e.getMessage());
        }
    }

    // Endpoints adicionales específicos para detalles de órdenes (comentados)

    // Buscar detalles por ID de orden
    /*
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByOrderId(@PathVariable Long orderId) {
        try {
            List<OrderDetail> orderDetails = orderDetailService.findByOrderId(orderId);
            return ResponseEntity.ok(orderDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Buscar detalles por ID de producto
    /*
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByProductId(@PathVariable Long productId) {
        try {
            List<OrderDetail> orderDetails = orderDetailService.findByProductId(productId);
            return ResponseEntity.ok(orderDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Calcular subtotal de un detalle específico
    /*
    @GetMapping("/{id}/subtotal")
    public ResponseEntity<Double> getOrderDetailSubtotal(@PathVariable Long id) {
        try {
            OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
            if (orderDetail != null) {
                Double subtotal = orderDetailService.calculateSubtotal(id);
                return ResponseEntity.ok(subtotal);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Actualizar cantidad de un detalle
    /*
    @PutMapping("/{id}/quantity")
    public ResponseEntity<String> updateOrderDetailQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        try {
            OrderDetail orderDetail = orderDetailService.getOrderDetailById(id);
            if (orderDetail != null) {
                orderDetailService.updateQuantity(id, quantity);
                return ResponseEntity.ok("Order detail quantity updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Order detail not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating order detail quantity: " + e.getMessage());
        }
    }
    */

    // Obtener total de todos los detalles de una orden específica
    /*
    @GetMapping("/order/{orderId}/total")
    public ResponseEntity<Double> getOrderTotal(@PathVariable Long orderId) {
        try {
            Double total = orderDetailService.calculateOrderTotal(orderId);
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    */

    // Eliminar todos los detalles de una orden específica
    /*
    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteAllOrderDetailsByOrderId(@PathVariable Long orderId) {
        try {
            orderDetailService.deleteByOrderId(orderId);
            return ResponseEntity.ok("All order details deleted successfully for order ID: " + orderId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting order details: " + e.getMessage());
        }
    }
    */
}