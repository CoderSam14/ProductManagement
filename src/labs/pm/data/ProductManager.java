/*
 *
 *  * Copyright © 2025 Sameer. All rights reserved.
 *
 *
 */

package labs.pm.data;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;


/**
 * @author acer
 **/
public class ProductManager {
    private Map<Product, List<Review>> products=new HashMap<>();

    private final ReentrantReadWriteLock lock=new ReentrantReadWriteLock();
    private final Lock writeLock= lock.writeLock();
    private final Lock readLock=lock.readLock();

    private Review[] reviews=new Review[5];

    private static Map<String, ResourceFormatter> formatters =
            Map.of("en-GB", new ResourceFormatter(Locale.UK),
                    "en-US", new ResourceFormatter(Locale.US),
                    "ru-RU", new ResourceFormatter(new Locale("ru", "RU")),
                    "fr-FR", new ResourceFormatter(Locale.FRANCE),
                    "zh-CN", new ResourceFormatter(Locale.CHINA));

    //private ResourceFormatter formatter;
    private final ResourceBundle config=ResourceBundle.getBundle("labs.pm.data.config");
    private final MessageFormat reviewFormat=new MessageFormat(config.getString("review.data.format"));
    private final MessageFormat productFormat=new MessageFormat(config.getString("product.data.format"));
    private static final Logger logger=Logger.getLogger(ProductManager.class.getName());

    //Paths
    private final Path reportsFolder=Path.of(config.getString("reports.folder"));
    private final Path dataFolder=Path.of(config.getString("data.folder"));
    private final Path tempFolder=Path.of(config.getString("temp.folder"));

    private static final ProductManager pm=new ProductManager();
    public static ProductManager getInstance(){
        return pm;
    }

    private ProductManager(){
    //    changeLocale(languageTag);
        loadAllData();
    }

//    public ProductManager(Locale locale){
//        this(locale.toLanguageTag());
//
//    }

    private static class ResourceFormatter{
        private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormat;
        private NumberFormat moneyFormat;

        private ResourceFormatter(Locale locale){
            this.locale=locale;
            this.resources=ResourceBundle.getBundle("labs.pm.data.resources",locale);
            this.dateFormat=DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
            this.moneyFormat=NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(Product product){
            String type=(product instanceof Food)? resources.getString("food"):resources.getString("drink");
             return MessageFormat.format(
                     resources.getString("product"),
                     product.getName(),
                     moneyFormat.format(product.getPrice()),
                     product.getRating().getStars(),
                     dateFormat.format(product.getBestBefore()),
                     type
             );
        }

        private String formatReview(Review review){
            return MessageFormat.format(
                    resources.getString("review"),
                    review.rating().getStars(),
                    review.comment()
            );
        }

        private String getText(String key){
            return resources.getString(key);
        }
    }

    public ResourceFormatter changeLocale(String LanguageTag){
        return formatters.getOrDefault(LanguageTag,formatters.get("en-GB"));

    }

    public static Set<String> getSupportedLocales(){
        return formatters.keySet();
    }
    //FOOD
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        Product product=null;
        try{
            writeLock.lock();
            product=new Food(id,name,price,rating,bestBefore);
            products.putIfAbsent(product,new ArrayList<>());
        }catch(Exception e){
            logger.log(Level.INFO,"Error adding product"+e.getMessage());
        }finally {
            writeLock.unlock();
        }
        return product;
    }
    //DRINK
    public Product createProduct(int id, String name, BigDecimal price, Rating rating){
        Product product=null;
        try{
            writeLock.lock();
            product=new Drink(id,name,price,rating);
            products.putIfAbsent(product,new ArrayList<>());
        }catch(Exception e){
            logger.log(Level.INFO,"Error adding product"+e.getMessage());
        }finally {
            writeLock.unlock();
        }
        return product;
    }


    public Product findProduct(int id) throws ProductManagerException{
//       Product result=null;
//       LOOP1:
//       for(Product product:products.keySet()){
//           if(product.getId()==id){
//               result=product;
//               break LOOP1;
//           }
//       }
//       return result;
        //Using Streams
        try{
            readLock.lock();
            return products.keySet().stream().filter(p->p.getId()==id).findFirst().orElseThrow(()->new ProductManagerException("Product with Id "+id+" not found"));
        }finally {
            readLock.unlock();
        }

    }

    public Product reviewProduct(int id,Rating rating,String comments){
        try {
            writeLock.lock();
            return reviewProduct(findProduct(id),rating,comments);
        } catch (ProductManagerException e) {
            logger.log(Level.INFO,e.getMessage());
            return null;
        }finally {
            writeLock.unlock();
        }
    }

    //NOT USED FOR NOW
    private Product reviewProduct(Product product,Rating rating,String comments){
        List<Review> reviews=products.get(product);
        products.remove(product,reviews);
        //WHY ? because apply rating returns new object!
        reviews.add(new Review(rating,comments));

//        int sum=0;
//
//        for(Review review:reviews){
//            sum+=review.rating().ordinal();
//        }
//
//        product=product.applyRating(Rateable.convert(Math.round((float)sum/reviews.size())));
        product=product.applyRating(Rateable.convert((int)Math.round(reviews.stream().mapToInt(r->r.rating().ordinal()).average().orElse(0))));
        this.products.put(product,reviews);
        return product;
    }

    public void printProductReport(int id,String languageTag,String Client){
        try {
            readLock.lock();
            printProductReport(findProduct(id),languageTag,Client);
        } catch (ProductManagerException e) {
            logger.log(Level.INFO,e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Error printing prodcut report "+e.getMessage(),e);
        }finally {
            readLock.unlock();
        }
    }

    private void printProductReport(Product product,String languageTag,String Client) throws IOException {
        ResourceFormatter formatter=changeLocale(languageTag);
        List<Review> reviews=products.get(product);
        Collections.sort(reviews);
        //StringBuilder txt=new StringBuilder();
        Path productFile=reportsFolder.resolve(MessageFormat.format(config.getString("report.file"),product.getId(),Client));
        try (PrintWriter out=new PrintWriter(new OutputStreamWriter(Files.newOutputStream(productFile, StandardOpenOption.CREATE),"UTF-8")) ){

            out.append(formatter.formatProduct(product)+System.lineSeparator());
            if (reviews.isEmpty()) {
                out.append(formatter.getText("no.reviews") +System.lineSeparator());
            } else {
                out.append(reviews.stream().map(r -> formatter.formatReview(r) +System.lineSeparator()).collect(Collectors.joining()));
            }
//        for(Review review:reviews){
//            txt.append(formatter.formatReview(review));
//            txt.append('\n');
//        }
           // System.out.println(txt);
        }
    }

    public void printProducts(Predicate<Product> filter,Comparator<Product> sorter,String languageTag) {
        try {
            readLock.lock();
            ResourceFormatter formatter = changeLocale(languageTag);
            StringBuilder txt = new StringBuilder();
            products.keySet().stream().sorted(sorter).filter(filter).forEach(p -> txt.append(formatter.formatProduct(p) + '\n'));
            System.out.println(txt);
        } finally {
            readLock.unlock();
        }
    }

    private List<Review> loadReviews(Product product){
        List<Review> reviews=null;
        Path file=dataFolder.resolve(MessageFormat.format(config.getString("reviews.data.file"),product.getId()));
        if(Files.notExists(file)){
            reviews=new ArrayList<>();
        }else{
            try {
                reviews= Files.lines(file, Charset.forName("UTF-8")).map(text->parseReview(text)).filter(review -> review!=null).collect(Collectors.toList());
            } catch (IOException e) {
                logger.log(Level.WARNING,"Error loading reviews"+e+e.getMessage());
            }
        }
        return reviews;
    }

    private Review parseReview(String text){
        Review review=null;
        try {
            Object[] values=reviewFormat.parse(text);
            review=new Review(Rateable.convert(Integer.parseInt((String)values[0])),(String)values[1]);

        } catch (ParseException | NumberFormatException e) {
            logger.log(Level.WARNING,"Error loading reviews "+e.getMessage());
        }
        return review;
    }

    private  Product loadProduct(Path file){
        Product product=null;
        try {
            product=parseProduct(Files.lines(dataFolder.resolve(file),Charset.forName("UTF-8")).findFirst().orElseThrow());
        } catch (Exception e) {
            logger.log(Level.WARNING,"Error loading product "+e.getMessage());
        }
        return product;
    }

    private Product parseProduct(String text){
        Product product=null;
        try {
            Object[] values=productFormat.parse(text);
            int id=Integer.parseInt((String)values[1]);
            String name=(String)values[2];
            BigDecimal price=BigDecimal.valueOf(Double.parseDouble((String)values[3]));
            Rating rating=Rateable.convert(Integer.parseInt((String)values[4]));
            switch ((String)values[0]){
                case "D":
                    product=new Drink(id,name,price,rating);
                    break;
                case "F":
                    LocalDate bestBefore=LocalDate.parse((String)values[5]);
                    product=new Food(id,name,price,rating,bestBefore);
            }
        } catch (ParseException | DateTimeParseException | NumberFormatException e) {
            logger.log(Level.WARNING,"Error parsing product "+text+" "+e.getMessage());
        }
        return product;
    }

    private void loadAllData(){
        try {
            products=Files.list(dataFolder)
                    .filter(file->file.getFileName().toString().startsWith("product"))
                    .map(file->loadProduct(file))
                    .filter(product -> product!=null)
                    .collect(Collectors.toMap(product -> product,product -> loadReviews(product)));
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Error loading data "+e.getMessage(),e);
        }
    }

    private void dumpData(){
        try{
            if(Files.notExists(tempFolder)){
                Files.createDirectory(tempFolder);
            }
            Path tempFile=tempFolder.resolve(
                    MessageFormat.format(config.getString("temp.file"),
                            Instant.now().toString().replace(":", "-").replace(".", "-")));
            try(ObjectOutputStream out=new ObjectOutputStream(
                    Files.newOutputStream(tempFile,StandardOpenOption.CREATE)
            )){
                out.writeObject(products);
                products=new HashMap<>();
            }

        }catch(IOException e){
            logger.log(Level.SEVERE,"Error dumping data "+e.getMessage(),e);
        }
    }

    private void restoreData(){
        try{
            Path tempFile=Files.list(tempFolder)
                    .filter(path->path.getFileName().toString().endsWith("tmp"))
                    .findFirst().orElseThrow();
            try(ObjectInputStream in=new ObjectInputStream(Files.newInputStream(tempFile,StandardOpenOption.DELETE_ON_CLOSE))){
                products=(HashMap)in.readObject();
            }

        }catch(Exception e){
            logger.log(Level.SEVERE,"Error restoring data "+e.getMessage(),e);
        }
    }

    public Map<String,String> getDiscount(String languageTag){
        try {
            readLock.lock();
            ResourceFormatter formatter = changeLocale(languageTag);
            return products.keySet().stream().collect(
                    Collectors.groupingBy(product -> product.getRating().getStars(),
                            Collectors.collectingAndThen(
                                    Collectors.summingDouble(
                                            product -> product.getDiscount().doubleValue()),
                                    discount -> formatter.moneyFormat.format(discount))));
        }finally {
            readLock.unlock();
        }
    }
}
