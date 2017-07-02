package farmfresh.business;

import java.text.NumberFormat;

/**
 * LineItem.java
 * Purpose: LineItem serves to record the Product and how many of each Product has been added.
 * It also records the cost of the Product when it was is purchased. After the Product is purchased
 * and the LineItem becomes part of an Invoice - we need to have record of what the price of the
 * product was at the time of purchase because the product price may change in the future.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
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
    public double getTotalCost(){
        return product.getPrice() * quantity;
    }

    public String getTotalCostInvoicedCurrencyFormat(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(getTotalCost());
    }

}
