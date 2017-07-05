package farmfresh.business;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: Cart represents a Shopping Cart that holds Products being purchased.
 * It contains a List of Line Items - 1 Per Product contained in the Cart.
 * The Line Items records the Product and how many of each Product is
 * contained in the Cart.  (e.g. LineItem 1: 10 Fuji Applies, LineItem 2: 5
 * Pints Blueberries, LineItem 3:  1 Seedless Watermelon)  NOTE:  There will
 * exist only one LineItem per Product/ProductID.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class Cart {

    /**
     * lineItems is a List of LineItems<br>
     * A Line Items records the Product's ID and how many
     * of each Product is contained in the Cart.  (e.g. LineItem 1: 10 Fuji Applies,
     * LineItem 2: 5 Pints Blueberries, LineItem 3:  1 Seedless Watermelon)
     * NOTE:  There will exist only one LineItem per Product/ProductID.
     */
    private List<LineItem> lineItems = new ArrayList<LineItem>(); //Empty List Created

    //CONSTRUCTOR
    public Cart() {
        //All variables automatically set to default values

    }

    /**
     * Returns the LineItem associated with the given Product ID or null if
     * no product exists matching that Product ID.
     * There will be no more than one Line Item for a given Product in each Cart
     * @param productId ID that uniquely identifies a Product
     */
    public LineItem getLineItem(long productId) {
        for (LineItem lineItem : lineItems) {
            if (lineItem.getProductId() == productId) {
                return lineItem;
            }
        }
        return null;
    }

    /**
     * @return all LineItems associated with the Cart.
     * A LineItem records what the Product is and how many are in the cart
     */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * Adds a LineItem to the cart.
     * A LineItem records what the Product is and how many are in the cart
     * NOTE:  There will be only one LineItem in the Cart per Product
     * (e.g.  Line Item:  2 Seedless Watermelons, Line Item: 1 Pint Raspberries)
     */
    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    /**
     * Removes a Line Item from the Cart<br>
     * In essense - it is removing all products of a particular Product ID
     * from the cart (e.g.  All Seedless Watermelons or All Blueberries, etc);
     */
    public void removeLineItem(LineItem lineItem) {
        lineItems.remove(lineItem);
    }

    //PUBLIC METHODS:  SPECIAL FUNCTIONALITY
    /**
     * @return  TRUE if the cart is Empty and FALSE if it is not.
     * Called within jsp files.
     */
    public boolean isEmpty(){
        return lineItems.isEmpty();
    }

    /**
     * @return Total Cost of all Products in Cart  (e.g.  10 apples cost $5)
     */
    public double getTotalCost() {
        double cartTotal = 0;
        for (LineItem lineItem : lineItems) {
            cartTotal += lineItem.getTotalCost();
        }
        return cartTotal;
    }

    /**
     * @return How many items exist in to the cart (e.g returns 10
     * if 5 apples and 5 pints of blueberries are in the cart).
     * Called within jsp files.
     */
    public int getCartQuantity() {
        int cartQuantity = 0;
        for (LineItem lineItem : lineItems) {
            cartQuantity += lineItem.getQuantity();
        }
        return cartQuantity;
    }

    /**
     * @return A Formatted String in Currency Format containing Total Cost
     * of the Items in the Cart.  Called within jsp files.
     */
    public String getTotalCostInCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(getTotalCost());
    }

}//End - Cart.java
