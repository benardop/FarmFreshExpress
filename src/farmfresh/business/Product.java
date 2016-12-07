package farmfresh.business;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 * Created by Mom and Dad on 11/4/2016.
 */
public class Product implements Serializable{
    //PRIVATE INSTANCE VARIABLES
    private long productId;
    private String productCode;
    private String description;
    private double price;

    //CONSTRUCTOR
    public Product(){
    }

    //PUBLIC METHODS:  GETTERS AND SETTERS
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) { this.productId = productId; }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) { this.productCode = productCode;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getPriceCurrencyFormat(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

    public String getImageURL(){
        String imageURL = "/images/" + productCode + "_cover.jpg";
        return imageURL;
    }

    public String getProductType(){
        return "Produce";
    }

}
