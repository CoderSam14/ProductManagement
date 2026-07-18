/*
 *
 *  * Copyright © 2025 Sameer. All rights reserved.
 *
 *
 */

package labs.pm.data;

<<<<<<< HEAD
import java.io.Serializable;

/**
 * @author acer
 **/
public record Review(Rating rating,String comment) implements  Comparable<Review>, Serializable {
=======
/**
 * @author acer
 **/
public record Review(Rating rating,String comment) implements  Comparable<Review> {
>>>>>>> c64f141c9d860cf60f417c45d8a8163595588a35


    @Override
    public int compareTo(Review other) {
        return other.rating.ordinal()-this.rating.ordinal();
        //-1 if current is greater than other
        //+1 if current is less than other
        //0 if they are equal
    }

}
