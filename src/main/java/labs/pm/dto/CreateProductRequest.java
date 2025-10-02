/*
 * Copyright © 2025 Sameer. All rights reserved.
 */

package labs.pm.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateProductRequest {
    private int id;
    private String name;
    private BigDecimal price;
    private String rating;
    private LocalDate bestBefore;
    
    public CreateProductRequest() {}
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
    
    public LocalDate getBestBefore() { return bestBefore; }
    public void setBestBefore(LocalDate bestBefore) { this.bestBefore = bestBefore; }
}
