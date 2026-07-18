/*
 * Copyright © 2025 Sameer. All rights reserved.
 */

package labs.pm.controller;

import labs.pm.data.*;
import labs.pm.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    
    private final ProductManager productManager;
    
    public ProductController() {
        this.productManager = new ProductManager(Locale.ENGLISH);
        initializeProducts();
    }
    
    private void initializeProducts() {
        // Initialize with some sample data
        productManager.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.NOT_RATED);
        productManager.createProduct(102, "Coffee", BigDecimal.valueOf(2.49), Rating.FOUR_STAR);
        productManager.createProduct(103, "Cake", BigDecimal.valueOf(3.99), Rating.FIVE_STAR, LocalDate.now().plusDays(2));
        productManager.createProduct(104, "Chocolate", BigDecimal.valueOf(2.99), Rating.FIVE_STAR);
        productManager.createProduct(105, "Cookie", BigDecimal.valueOf(1.49), Rating.TWO_STAR, LocalDate.now().plusDays(1));
        
        // Add some reviews
        productManager.reviewProduct(101, Rating.FOUR_STAR, "Nice hot cup of tea");
        productManager.reviewProduct(101, Rating.THREE_STAR, "Good but could be stronger");
        productManager.reviewProduct(102, Rating.FIVE_STAR, "Perfect coffee!");
        productManager.reviewProduct(103, Rating.FOUR_STAR, "Delicious cake");
    }
    
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productManager.getAllProducts().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
        Product product = productManager.findProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDTO(product));
    }
    
    @PostMapping
    public ProductDTO createProduct(@RequestBody CreateProductRequest request) {
        Product product;
        if (request.getBestBefore() != null) {
            product = productManager.createProduct(
                request.getId(),
                request.getName(),
                request.getPrice(),
                Rating.valueOf(request.getRating()),
                request.getBestBefore()
            );
        } else {
            product = productManager.createProduct(
                request.getId(),
                request.getName(),
                request.getPrice(),
                Rating.valueOf(request.getRating())
            );
        }
        return convertToDTO(product);
    }
    
    @PostMapping("/{id}/reviews")
    public ResponseEntity<ProductDTO> addReview(@PathVariable int id, @RequestBody ReviewRequest request) {
        Product product = productManager.reviewProduct(
            id,
            Rating.valueOf(request.getRating()),
            request.getComment()
        );
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertToDTO(product));
    }
    
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> getProductReviews(@PathVariable int id) {
        Product product = productManager.findProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        List<ReviewDTO> reviews = productManager.getProductReviews(id).stream()
                .map(review -> new ReviewDTO(review.rating().name(), review.comment()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviews);
    }
    
    @GetMapping("/{id}/report")
    public ResponseEntity<String> getProductReport(@PathVariable int id) {
        String report = productManager.getProductReport(id);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }
    
    @PutMapping("/locale/{locale}")
    public ResponseEntity<Void> changeLocale(@PathVariable String locale) {
        productManager.changeLocale(locale);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/locales")
    public Set<String> getSupportedLocales() {
        return ProductManager.getSupportedLocales();
    }
    
    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getRating().name(),
            product.getRating().getStars(),
            product.getBestBefore(),
            product.getDiscount(),
            product instanceof Food ? "FOOD" : "DRINK"
        );
    }
}
