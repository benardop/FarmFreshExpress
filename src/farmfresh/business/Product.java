package farmfresh.business;

/**
 * Created by Mom and Dad on 11/4/2016.
 */
public class Product {
    //PRIVATE INSTANCE VARIABLES
    private String code;
    private String description;
    private double price;

    //PUBLIC METHODS:  SETTERS AND GETTERS
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    //PUBLIC METHODS:  SPECIAL FUNCTIONALITY
    public String getPriceCurrencyFormat(){
        return "currencyFormat";  //TODO  complete this code
    }

    public String getImageURL(){
        return "imageURL";
    }

    public String getProductType(){
        return "productType";
    }

}
