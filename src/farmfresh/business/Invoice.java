package farmfresh.business;

import java.util.*;

/**
 * Created by Mom and Dad on 11/4/2016.
 */
public class Invoice {
    // PRIVATE INSTANCE VARIABLES
    private User user;
    private List<LineItem> lineItemList;
    private Date invoiceDate;

    //PUBLIC METHODS:  SETTERS AND GETTERS
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    //PUBLIC METHODS:  SPECIALTY FUNCTIONALITY
    public double getInvoiceTotal(){
        double invoiceTotal = 0;
        for (LineItem lineItem: lineItemList) {
            invoiceTotal += lineItem.getTotal();
        }
        return invoiceTotal;
    }

    public String getInvoiceTotalCurrencyFormat(){
        return "Currency Format";  //TODO Needs to be coded
    }

}
