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
<<<<<<< HEAD
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import labs.pm.data.ProductManager;


public class Shop {
    public static void main(String[] args) throws Exception {
        ProductManager pm=ProductManager.getInstance();

        AtomicInteger clientCount=new AtomicInteger(0);

        Callable<String> client=()->{
            String clientId="Client"+clientCount.incrementAndGet();
            String threadName=Thread.currentThread().getName();
            int productId= ThreadLocalRandom.current().nextInt(63)+101;
            String languageTag=ProductManager.getSupportedLocales()
                    .stream()
                    .skip(ThreadLocalRandom.current().nextInt(4))
                    .findFirst().get();
            StringBuilder log=new StringBuilder();
            log.append(clientId + " " + threadName + "\n-\tstart of log\t-\n");
            log.append("\n-\tend of log\t-\n");
            log.append(pm.getDiscount (languageTag)
                    .entrySet()
                    .stream()
                    .map(entry->entry.getKey()+"\t"+entry.getValue())
                    .collect (Collectors.joining("\n")));
            Product product=pm.reviewProduct(productId,Rating.FOUR_STAR,"Yet another review");
            log.append((product!=null)
                    ? "\nProduct "+productId+" was reviewed\n"
                    : "\nProduct "+productId+" was not reviewed\n");
            return log.toString();
        };
        List<Callable<String>> clients= Stream.generate(()->client).limit(5).collect(Collectors.toList());
        ExecutorService executorService= Executors.newFixedThreadPool(3);
        try{
            List<Future<String>> results=executorService.invokeAll(clients);
            executorService.shutdown();
            results.stream().forEach(result-> {
                try {
                    System.out.println(result.get());
                } catch (InterruptedException | ExecutionException e) {
                    Logger.getLogger(Shop.class.getName())
                            .log(Level.SEVERE,"Failed to retrieve client list",e);
                }
            });
        }catch (InterruptedException e){
            Logger.getLogger(Shop.class.getName())
                    .log(Level.SEVERE,"Failed to invoke clients",e);
        }
=======
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
>>>>>>> c64f141c9d860cf60f417c45d8a8163595588a35
    }
}