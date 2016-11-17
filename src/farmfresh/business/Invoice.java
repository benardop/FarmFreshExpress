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
    private long invoiceNumber;
    private boolean isProcessed;

    //CONSTRUCTORS
    public Invoice(){
        //All variables automatically set to default values
    }

    //PUBLIC METHODS:  GETTERS AND SETTERS
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public boolean isProcessed() { return isProcessed; }

    // idProcessed flag set only when Invoice is inserted into the Database
    //public void setProcessed(boolean processed) { isProcessed = processed; }


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
