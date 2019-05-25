package Model;

import java.util.List;

public class Product {
    protected String asin;
    protected String title;
    protected double price;
    private String imgUrl;
    protected String brand;
    private Vendor vendor;
    private List<Feedback> feedbackList;


    public Product(String asin, String title, double price, String imgUrl, String brand) {
        this.asin = asin;
        this.title = title;
        this.price = price;
        this.imgUrl = imgUrl;
        this.brand = brand;
    }
}
