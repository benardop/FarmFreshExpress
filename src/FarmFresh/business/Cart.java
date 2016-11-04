package FarmFresh.business;

import java.util.ArrayList;
import java.util.List;

//TODO - javadoc documentation
//TODO - exception processing

 /* Created by Mom and Dad on 11/4/2016.
 */
public class Cart {
    //PRIVATE MEMBER VARIABLES
    private List<LineItem> lineItems = new ArrayList<LineItem>(); //Empty List Created

    //PUBLIC METHODS:  SETTERS AND GETTERS
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    //PUBLIC METHODS:  SPECIAL FUNCTIONALITY
    public void addLineItem(LineItem lineItem){
        lineItems.add(lineItem);
    }
    public void removeLineItem(LineItem lineItem){
        lineItems.remove(lineItem);
    }
}
