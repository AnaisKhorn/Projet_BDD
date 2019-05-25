package Model;

import java.util.List;

public class Vendor {
    private String vendorId ;
    private String country ;
    private String industry ;
    private List<Product> productList;

    public Vendor(String vendorId, String country, String industry) {
        this.vendorId = vendorId;
        this.country = country;
        this.industry = industry;
    }
}
