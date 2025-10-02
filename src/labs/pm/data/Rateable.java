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
@FunctionalInterface
public interface Rateable<T> {
    public static final Rating DEFAULT_RATING=Rating.NOT_RATED;

    public abstract T applyRating(Rating rating);//Abstract keyword not really needed here

    public default Rating getRating(){
        return DEFAULT_RATING;
    }
    public default T applyRating(int stars){
        return applyRating(convert(stars));
    }

    public static Rating convert(int stars) {
        return (stars >= 0 && stars <= 5) ? Rating.values()[stars] : DEFAULT_RATING;
    }
    //.values() returns an array of all possible enums. Final Array[1] means 2nd enum



}
