/*
 * Copyright © 2025 Sameer. All rights reserved.
 */

package labs.pm.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductDTO {
    private int id;
    private String name;
    private BigDecimal price;
    private String rating;
    private String ratingStars;
    private LocalDate bestBefore;
    private BigDecimal discount;
    private String type;
    
    public ProductDTO() {}
    
    public ProductDTO(int id, String name, BigDecimal price, String rating, 
                     String ratingStars, LocalDate bestBefore, BigDecimal discount, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.ratingStars = ratingStars;
        this.bestBefore = bestBefore;
        this.discount = discount;
        this.type = type;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
    
    public String getRatingStars() { return ratingStars; }
    public void setRatingStars(String ratingStars) { this.ratingStars = ratingStars; }
    
    public LocalDate getBestBefore() { return bestBefore; }
    public void setBestBefore(LocalDate bestBefore) { this.bestBefore = bestBefore; }
    
    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
