import javax.swing.*;

/**
 * Created by Mom and Dad on 11/2/2016.
 *
 * Class:  Product
 * Desc:  Product is the base class for all items that can be sold and purchased
 */
abstract class Product  {  //BEN - by default it extends Object - correct?

    private String itemName;
    private String producersName;
    private Double price;  //BEN when should I use a wrapper class vs a primitive
                             //BEN is there a class I should use to represent currency?
    private String unit;  //price per unit e.g.  $5/lb, $1 each, $1 per bunch
    private ImageIcon image; //BEN what would I use for images

    //BEN - what is your feeling surrounding the use of protected vs private (with getters and setters)
    //  it was suggested I stick with private data - and getters and setters

    //SETTERS AND GETTERS
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setProducersName(String producersName) {
        this.producersName = producersName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public String getProducersName() {
        return producersName;
    }

    public Double getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public ImageIcon getImage() {
        return image;
    }

    //CONSTRUCTORS
    protected Product(String itemName, double price){
        this.itemName = itemName;
        this.producersName = "test";
        this.price = price;
        this.image = null;
    }





}
