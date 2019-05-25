package Model;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private String personId;
    private Date orderDate;
    private double totalPrice ;
    private List<OrderLine> orderLines;
    private Invoice invoice;

    public Order(String orderId, String personId, Date orderDate, double totalPrice, List<OrderLine> orderId1) {
        this.orderId = orderId;
        this.personId = personId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        orderLines = orderId1;
    }
}
