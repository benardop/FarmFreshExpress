package farmfresh.business;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Cart.java
 * Purpose: Cart represents a Shopping Cart that holds Products being purchased.
 * It contains a List of the Products and how many of each Product is contained in the Cart.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class Cart {

    private List<LineItem> lineItems = new ArrayList<LineItem>(); //Empty List Created

    //CONSTRUCTOR
    public Cart() {
        //All variables automatically set to default values

    }

    //PUBLIC METHODS:  SIMPLE
    /**
     * Returns the LineItem associated with the given Product ID.
     * There will be no more than one Line Item for a given Product in each Cart
     *
     * @param productId ID that uniquely identifies a Product
     * @return the LineItem for the given Product in the Cart
     */
    public LineItem getLineItem(long productId) {
        for (LineItem lineItem : lineItems) {
            if (lineItem.getProductId() == productId) {
                return lineItem;
            }
        }
        return null;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);
    }

    //PUBLIC METHODS:  SPECIAL FUNCTIONALITY
    /**
     *
     * @return  Returns TRUE if the cart is Empty and FALSE if it is not.
     */
    public boolean isEmpty(){
        return lineItems.isEmpty();
    }

    /**
     * @return Total Cost of all Products in Cart
     */
    public double getCartTotal() {
        double cartTotal = 0;
        for (LineItem lineItem : lineItems) {
            cartTotal += lineItem.getTotalCost();
        }
        return cartTotal;
    }

    /**
     * @return How many items have beed added to the cart
     */
    public int getCartQuantity() {
        int cartQuantity = 0;
        for (LineItem lineItem : lineItems) {
            cartQuantity += lineItem.getQuantity();
        }
        return cartQuantity;
    }

    /**
     * @return Formatted String containing Total Cost of Items in Cart in Currency Format
     */
    public String getCartTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(getCartTotal());
    }
}
