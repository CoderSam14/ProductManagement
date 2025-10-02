/*
 *
 *  * Copyright © 2025 Sameer. All rights reserved.
 *
 *
 */
/**
 * {@code Product} class represents properties and behaviors of
 * product objects in the Product Management System.
 * <br>
 * Each product has an id, name, and price
 * <br>
 * Each product can have a discount, calculated based on a
 * {@link DISCOUNT_RATE discount rate}
 * @version 1.0
 * @author sameer
 */



package labs.pm.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

import static labs.pm.data.Rating.*;

public sealed abstract class Product implements Rateable<Product> permits Food,Drink {
    private final int id;
    private final String name;
    private final BigDecimal price;
    public static final BigDecimal DISCOUNT_RATE=BigDecimal.valueOf(0.1);
    private final Rating rating;
    //Now my Product instances are immutable!

    //Now java won't provide an empty constructor!
    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public abstract Product applyRating(Rating newRating);


    //Id
    public int getId() {
        return id;
    }
//    public void setId(final int id) {
//        this.id = id;
//    }

    //Name
    public String getName() {
        return name;
    }
//    public void setName(final String name) {
//        this.name = name;
//    }

    //Price
    public BigDecimal getPrice() {
        return price;
    }
//    public void setPrice(final BigDecimal price) {
//        this.price = price;
//    }

    //Rating
    public Rating getRating() {
        return rating;
    }

    public LocalDate getBestBefore(){
        return LocalDate.now();
    }

    /**
     * A constant that defines a
     * {@link java.math.BigDecimal BigDecimal} value of the discount rate
     * <br>
     * Discount rate is 10%
     * */
    public BigDecimal getDiscount(){
        return price.multiply(DISCOUNT_RATE).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating.getStars()+" " + "BestBeforeDate:"+
                        " "+ getBestBefore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Product product) {
            return id==product.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
