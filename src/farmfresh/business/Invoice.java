package farmfresh.business;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Invoice.java
 * Purpose: Invoice represents an Invoice created once a User makes a purchase.
 * It contains a List of the Products and how many of each Product was purchased.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class Invoice {
    // PRIVATE INSTANCE VARIABLES
    private User user;
    private List<LineItem> lineItems;
    private Date invoiceDate;
    private long invoiceNumber;     // Stored as invoiceID in Invoice DB Table
    private boolean isProcessed;

    //CONSTRUCTOR
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

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
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

    //************************************************************************
    // isProcessed flag set only when Invoice is inserted into the Database
    //public void setProcessed(boolean processed) { isProcessed = processed; }
    //************************************************************************

    //PUBLIC METHODS:  SPECIALTY FUNCTIONALITY
    public double getInvoiceTotal(){
        double invoiceTotal = 0;
        for (LineItem lineItem: lineItems) {
            invoiceTotal += lineItem.getTotalCost();
        }
        return invoiceTotal;
    }

    public String getInvoiceTotalCurrencyFormat(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(getInvoiceTotal());
    }

    public String getInvoiceDateDefaultFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy' 'HH:mm:ss");
        return dateFormat.format(getInvoiceDate());
    }

}
