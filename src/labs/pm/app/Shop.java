/*
 *
 *  * Copyright © 2025 Sameer. All rights reserved.
 *
 *
 */

package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;
import java.math.BigDecimal.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;

import labs.pm.data.ProductManager;

public class Shop {
    public static void main(String[] args) throws Exception {
        ProductManager pm=new ProductManager(Locale.UK);
        pm.createProduct(101,"Tea",BigDecimal.valueOf(1.99),Rating.NOT_RATED);
        pm.printProductReport(101);
        System.out.println("AFTER REVIEWS");
        pm.reviewProduct(101, Rating.FOUR_STAR, "Nice hot cup of tea");
        pm.reviewProduct(101, Rating.TWO_STAR, "Rather weak tea");
        pm.reviewProduct(101, Rating.FOUR_STAR, "Fine tea");
        pm.reviewProduct(101, Rating.FOUR_STAR, "Good tea");
        pm.reviewProduct(101, Rating.FIVE_STAR, "Perfect tea");
        pm.reviewProduct(101, Rating.THREE_STAR, "Just add some lemon");
        pm.printProductReport(101);
        pm.createProduct(102,"Coffee",BigDecimal.valueOf(1.99),Rating.FOUR_STAR);
        pm.createProduct(103,"Cake",BigDecimal.valueOf(3.99),Rating.FIVE_STAR, LocalDate.now().plusDays(2));
        pm.createProduct(105,"Cookie",BigDecimal.valueOf(3.99),Rating.TWO_STAR,LocalDate.now());
        pm.createProduct(104,"Chocolate",BigDecimal.valueOf(2.99),Rating.FIVE_STAR);
        pm.createProduct(104,"Chocolate",BigDecimal.valueOf(2.99),Rating.FIVE_STAR,LocalDate.now().plusDays(2));

        pm.changeLocale("ru-RU");
        pm.printProductReport(101);
//        Comparator<Product> ratingSorter=(p1,p2)->p2.getRating().ordinal()-p1.getRating().ordinal();
//        Comparator<Product> priceSorter=(p1,p2)->p2.getPrice().compareTo(p1.getPrice());
//        pm.printProducts(ratingSorter.thenComparing(priceSorter));
    }
}