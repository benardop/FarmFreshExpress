package farmfresh.business;

import java.text.NumberFormat;

/**
 * Created by Mom and Dad on 11/4/2016.
 */
public class LineItem {
    //PRIVATE INSTANCE VARIABLES
    private Product product;
    private int quantity;

    //CONSTRUCTOR
    public LineItem(){
        //All variables automatically set to default values  //TODO - is this correct?
    }

    //PUBLIC METHODS: GETTERS AND SETTERS
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getProductId(){
        if (product == null)
            return 0;
        else
            return product.getProductId();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(int nbr) {this.quantity += nbr;}

    //PUBLIC METHODS:  SPECIAL FUNCTIONALITY
    public double getTotal(){
        return product.getPrice() * quantity;
    }

    public String getTotalCurrencyFormat(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(getTotal());
    }

}
