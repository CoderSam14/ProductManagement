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
//2606 is for white star
//2605 is for black star
public enum Rating {
    NOT_RATED("☆☆☆☆☆"),
    ONE_STAR("★☆☆☆☆"),
    TWO_STAR("\u2605\u2605\u2606\u2606\u2606"),
    THREE_STAR("\u2605\u2605\u2605\u2606\u2606"),
    FOUR_STAR("\u2605\u2605\u2605\u2605\u2606"),
    FIVE_STAR("\u2605\u2605\u2605\u2605\u2605");
    //We need to provide a variable to store this and constructor


    private String stars;

    Rating(String stars) {
        this.stars = stars;
    }
    public String getStars() {
        return stars;
    }
}
