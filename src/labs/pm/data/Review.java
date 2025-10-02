/*
 *
 *  * Copyright © 2025 Sameer. All rights reserved.
 *
 *
 */

package labs.pm.data;

/**
 * @author acer
 **/
public record Review(Rating rating,String comment) implements  Comparable<Review> {


    @Override
    public int compareTo(Review other) {
        return other.rating.ordinal()-this.rating.ordinal();
        //-1 if current is greater than other
        //+1 if current is less than other
        //0 if they are equal
    }

}
