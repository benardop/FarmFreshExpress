package farmfresh.business;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

 /* Created by Mom and Dad on 11/4/2016.
 */
public class Cart {
    //PRIVATE INSTANCE VARIABLES
    private List<LineItem> lineItems = new ArrayList<LineItem>(); //Empty List Created

     //CONSTRUCTOR
     public Cart(){
         //All variables automatically set to default values
     }

    //PUBLIC METHODS:  GETTERS AND SETTERS
    public List<LineItem> getLineItems() {
         return lineItems;
     }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    //PUBLIC METHODS:  SPECIAL FUNCTIONALITY
    public void addLineItem(LineItem lineItem){
        lineItems.add(lineItem);
    }

    public void removeLineItem(LineItem lineItem){
        lineItems.remove(lineItem);
    }

    public int getCount(){
        return lineItems.size();
    }

    public LineItem getLineItem(long productId){
            for (LineItem lineItem: lineItems) {
                if (lineItem.getProductId() == productId) {
                    return lineItem;
                }
            }
        return null;
    }

     public boolean isEmpty(){

         int numLineItems = lineItems.size();
         if (numLineItems > 0)
             return false;
         else
             return true;
     }

     public double getCartTotal(){
         double cartTotal = 0;
         for (LineItem lineItem: lineItems) {
             cartTotal += lineItem.getTotal();
         }
         return cartTotal;
     }

     public int getCartQuantity(){
         int cartQuantity = 0;
         for (LineItem lineItem: lineItems) {
             cartQuantity += lineItem.getQuantity();
         }
         return cartQuantity;
     }

     public String getCartTotalCurrencyFormat(){
         NumberFormat currency = NumberFormat.getCurrencyInstance();
         return currency.format(getCartTotal());
     }



}
