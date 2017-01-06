package farmfresh.business;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 * Created by Mom and Dad on 11/4/2016.
 */
public class Product implements Serializable{
    //PRIVATE INSTANCE VARIABLES
    private long productId;
    private long productTypeId;
    private String productTypeName;
    private String productCode;
    private String name;
    private String description;
    private String imageId;
    private double price;
    private boolean inSeason;

    //CONSTRUCTOR
    public Product(){
    }

    //PUBLIC METHODS:  GETTERS AND SETTERS
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductType() {
        return productTypeName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInSeason() {
        return inSeason;
    }

    public void setInSeason(boolean inSeason) {
        this.inSeason = inSeason;
    }

    public String getPriceCurrencyFormat(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

    public String getImageURL(){
        String imageURL = "/images/" + imageId;
        return imageURL;
    }

}
