/*
 *
 *  * Copyright © 2025 Sameer. All rights reserved.
 *
 *
 */

package labs.pm.data;

import java.io.Serializable;

/**
 * @author acer
 **/
public record Review(Rating rating,String comment) implements  Comparable<Review>, Serializable {


    @Override
    public int compareTo(Review other) {
        return other.rating.ordinal()-this.rating.ordinal();
        //-1 if current is greater than other
        //+1 if current is less than other
        //0 if they are equal
    }

}
