/*
 * Copyright © 2025 Sameer. All rights reserved.
 */

package labs.pm.dto;

public class ReviewRequest {
    private String rating;
    private String comment;
    
    public ReviewRequest() {}
    
    public ReviewRequest(String rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
    
    // Getters and setters
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
    
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
