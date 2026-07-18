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
    }
}