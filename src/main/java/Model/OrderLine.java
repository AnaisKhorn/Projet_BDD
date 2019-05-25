package Model;

public class OrderLine extends Product{
    private String productId;

    public OrderLine(String productId, String asin, String title, double price, String brand) {
        super(asin,title,price,null,brand);
        this.productId = productId;
    }
}
