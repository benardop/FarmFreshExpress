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

    /**
     * The Product the Line Item is associated with (e.g. Strawberries, Eggs, etc)
     */
    private Product product;

    /**
     * How many of each Product (e.g. 10 Eggs)
     */
    private int quantity;

    //CONSTRUCTOR
    public LineItem(){
        //All variables automatically set to default values  //TODO - is this correct?
    }

    /**
     * @return The Product associated with the Line Item
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product The Product associated with the Line Item
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return The Product's Unique Identifier
     */
    public long getProductId(){ return product.getProductId(); }

    /**
     * @return How much of the List Item's Product there is
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity How many of the product exists
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @param nbr Increase the Product's Quantity by the given Number
     */
    public void increaseQuantity(int nbr) {this.quantity += nbr;}

    /**
     * @return Total Cost of Line Item (Product Price * Quantity)
     */
    public double getTotalCost(){
        return product.getPrice() * quantity;
    }

    /**
     * @return  A Formatted String in Currency Format containing the Total Cost
     * of the Line Item (Product Price * Quantity). Called within jsp files.
     */
    public String getTotalCostInCurrencyFormat(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(getTotalCost());
    }

}//End - LineItem.java
