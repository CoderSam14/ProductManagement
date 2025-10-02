/*
 *
 *  * Copyright © 2025 Sameer. All rights reserved.
 *
 *
 */

package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author acer
 **/
//BY DEFAULT INVOKES CONSTRUCTOR WITH NO PARAMETER
    //FINAL CLASSES CANNOT BE EXTENDED
public final class Drink extends Product {

    Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }

    @Override
    public BigDecimal getDiscount() {
        LocalTime now=LocalTime.now();
        return (now.isAfter(LocalTime.of(17,30)) && now.isBefore(LocalTime.of(18,30))) ? super.getDiscount():BigDecimal.ZERO;

    }

    public Drink applyRating(Rating newRating) {
        return new Drink(getId(),getName(),getPrice(),newRating);
    }
}
