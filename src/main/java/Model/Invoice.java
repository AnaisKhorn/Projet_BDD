package Model;

import java.util.Date;
import java.util.List;

public class Invoice {
    private String invoiceId;
    private String orderID;
    private String personId;
    private Date orderDate;
    private double totalPrice;
    private List<OrderLine> orderLines;
    private Order order;

    public Invoice(String invoiceId, String personId, Date orderDate, double totalPrice, List<OrderLine> orderLines) {
        this.invoiceId = invoiceId;
        this.personId = personId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderLines = orderLines;
    }
}
