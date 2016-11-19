package farmfresh.business;

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

}
