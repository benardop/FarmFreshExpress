package farmfresh.business;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 * Purpose: Product represents a Product available to purchase
 * (e.g. Blue Berries, Black Berries, Fuji Apples, Red Delicious Apples)
 * Each Product will have a Particular Product Type that will be used
 * to group the Products for sale (e.g.  Fruit, Vegetables, Floral, etc)
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class Product implements Serializable{

    /**
     * Uniquely identifies a Product
     */
    private long productId;

    /**
     * Identifies the Product Type the Product belongs to
     * (e.g. Fuji Apples belong to the Fruit Product Type)
     */
    private long productTypeId;

    /**
     * Name of the Product Type (e.g. Fruits, Veggies)
     * Product Type Name is stored on the Product Object,
     * but is pulled in from the producttype table
     */
    private String productTypeName;

    /**
     * Product Code is a Unique Product Identifier - used by the seller -
     * not the system (NOT CURRENTLY USED)
     */
    private String productCode;

    /**
     * Name of Product
     */
    private String name;

    /**
     * Description of the Product
     */
    private String description;

    /**
     * Image/Picture of the Product
     */
    private String imageId;

    /**
     * Price/Cost of the product
     * NOTE:  For V1 - there is only a single price per product.
     * We do not handle the scenario where a given product could be sold in different ways
     * (e.g. Bagels - price per dozen, half-dozen or single)
     * At this time - if the seller wants to sell that way...
     * Dozen Bagels - would be one product
     * Half Dozen Bagels - would be another product and
     * Single Bagel would be a third product.
     */
    private double price;

    /**
     * Flag indicating if the Product isAvailable
     */
    private boolean isAvailable;

    //CONSTRUCTOR
    public Product(){
    }

    /**
     * @return The Product's Unique Identifier
     */
    public long getProductId() {
        return productId;
    }

    /**
     *
     * @param productId The Product's Unique Identifier
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }

    /**
     * @return  The Unique Identifier of the Product's Product Type
     */
    public long getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId  The Unique Identifier of the Product's Product Type
     */
    public void setProductTypeId(long productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * @param productTypeName The Name of the Product's Product Type (e.g. Fruits, Veggies).
     * Product Type Name is stored on the Product Object, but is pulled in from the
     * producttype table
     */
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

//    public String getProductType() {
//        return productTypeName;
//    }
//
//    public String getProductCode() {
//        return productCode;
//    }

    /**
     * @param productCode Product Code is a Unique Product Identifier -
     * used by the seller - not the system (NOT CURRENTLY USED)
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return Product's Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Product's Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Product's Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Product's Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Image/Picture of the Product
     */
    public String getImageId() {
        return imageId;
    }

    /**
     * @param imageId  Image/Picture of the Product
     */
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /**
     * @return  Product's Price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price Product's Price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return TRUE if the Product is Available, FALSE otherwise
     */
    public boolean isAvailable() { return isAvailable; }

    /**
     * @param isAvailable FLAG indicating if the Product is Available
     */
    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    /**
     * @return A Formatted String in Currency Format containing the Product's Price.
     * Called within jsp files.
     */
    public String getPriceInCurrencyFormat(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

    /**
     * @return the URL of the Image associated with this Product.  Called within jsp files.
     */
    public String getImageURL(){
        String imageURL = "/images/" + imageId;
        return imageURL;
    }

}
